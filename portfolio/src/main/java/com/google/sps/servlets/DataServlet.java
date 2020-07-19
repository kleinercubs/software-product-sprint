// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Entity;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/** Servlet that returns some example content. TODO: modify this file to handle comments data */
@WebServlet("/submit-comment")
public class DataServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    UserService userService = UserServiceFactory.getUserService();
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    
    String content = request.getParameter("content");
    String email = userService.getCurrentUser().getEmail();
    String[] chk = request.getParameterValues("anonymous");
    boolean isAnonymous = (chk != null);
    long timestamp = System.currentTimeMillis();
    
    String nickname = getUserNickname(userService.getCurrentUser().getEmail());
    String author = request.getParameter("author");
    if (nickname == null) {
      Entity infoEntity = new Entity("UserInfo");
      infoEntity.setProperty("email", email);
      infoEntity.setProperty("nickname", author);
      datastore.put(infoEntity);
    }
    
    System.out.println("dataservlet:" + nickname + " " + author);

    Entity commentEntity = new Entity("Comments");
    commentEntity.setProperty("email", email);
    commentEntity.setProperty("isAnonymous", isAnonymous);
    commentEntity.setProperty("author", author);
    commentEntity.setProperty("content", content);
    commentEntity.setProperty("timestamp", timestamp);

    datastore.put(commentEntity);

    response.sendRedirect("/index.html");
  }

  private String getUserNickname(String email) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("UserInfo")
                    .setFilter(new Query.FilterPredicate("email", Query.FilterOperator.EQUAL, email));
    PreparedQuery results = datastore.prepare(query);
    Entity entity = results.asSingleEntity();
    if (entity == null) {
      return null;
    }
    String nickname = (String) entity.getProperty("nickname");
    return nickname;
  }
}
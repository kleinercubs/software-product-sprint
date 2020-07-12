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

import com.google.gson.Gson;
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

  public class Comment {
    private String title;
    private String author;
    private String content;
    public Comment(){}
    public Comment(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String content = getContent(request);
    response.setContentType("text/html;");
    response.getWriter().println(content);
    response.sendRedirect("/index.html");
  }

  /** Returns the comment entered */
  private String getContent(HttpServletRequest request) {
    // Get the input from the form.
    String content = request.getParameter("comment");
    return content;
  }
}
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
@WebServlet("/data")
public class DataServlet extends HttpServlet {

  public class Test {
    private String title;
    private String author;
    private String content;
    public Test(){}
    public Test(String title, String author, String content){
        this.title = title;
        this.author = author;
        this.content = content;
    }
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList test = new ArrayList<>();
    test.add(new Test("first", "alice", "hi"));
    test.add(new Test("second", "bob", "hello"));
    test.add(new Test("third", "cathy", "hey"));
    Gson gson = new Gson();
    String json = gson.toJson(test);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
}

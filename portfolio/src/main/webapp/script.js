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

function loadComments(){
  fetch('/list-comments').then(response => response.json()).then((comments) => {
    var newDate = new Date();
    const commentListElement = document.getElementById('comment-list');
    comments.innerText='';
    comments.forEach((comment) => {
      console.log(comment);
      newDate.setTime(comment.timestamp);
      commentListElement.appendChild(createCommentElement(comment.content + '\n' + 'by ' + comment.email + ' ' + comment.author + '\n' + newDate.toLocaleString('chinese',{hour12:false})));
    })
  });
}

/** Creates an <li> element containing text. */
function createCommentElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

function getLoginStatus(){
  fetch('/user-auth').then(response => response.json()).then((user) => {
    const CommentBox = document.getElementById('comment-box');
    console.log(user);
    console.log(user.status + " " + user.url);
    if (user.status == "LoggedIn"){
      CommentBox.innerHTML = "<form action=\"/submit-comment\" method=\"POST\">"
                          + "author: <input type=\"text\" name=\"author\" placeholder=\"default as anonymous\">"
                          + "<br/>"
                          + "content: <input type=\"text\" name=\"content\" size=\"15\" style=\"width:500px; height:80px;\" required=\"required\">"
                          + "<br/><br/>"
                          + "<input type=\"submit\" />"
                          + "<a href =\"" + user.url +"\"> Logout </a>"
                          + "</form>";
    } else {
      CommentBox.innerHTML = "<a href =\"" + user.url +"\"> Login to leave a comment </a>";
    }
  })
}

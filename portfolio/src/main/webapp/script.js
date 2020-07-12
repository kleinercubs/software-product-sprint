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


/**
 * uses arrow functions to shorten the above code
 */
function getGreetingUsingArrowFunctions() {
  fetch('/data').then(response => response.text()).then((test) => {
    const testListElement = document.getElementById('greeting-container');
    testListElement.innerHTML = '';
    
    data = eval("(" + test + ")");
    console.log(data);
    testListElement.appendChild(
        createListElement(data[0].author));
    testListElement.appendChild(
        createListElement(data[1].author));
    testListElement.appendChild(
        createListElement(data[2].author));
  });
}

/** Creates an <li> element containing text. */
function createListElement(text) {
  const liElement = document.createElement('li');
  liElement.innerText = text;
  return liElement;
}

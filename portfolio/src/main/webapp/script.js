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
 * Fetches data from the server and adds it to the DOM.
 */
function getGreeting() {
  console.log('Fetching greeting.');
  const responsePromise = fetch('/data');
  responsePromise.then(handleResponse);
}

/**
 * Handles response by converting it to text and passing the result to
 * addGreetingToDom().
 */
function handleResponse(response) {
  console.log('Handling the response.');
  const textPromise = response.text();
  textPromise.then(addGreetingToDom);
}

/** Adds greeting to the DOM. */
function addGreetingToDom(greeting) {
  console.log('Adding greeting to dom: ' + greeting);

  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerHTML = greeting;
}

/**
 * uses arrow functions to shorten the above code
 */
function getGreetingUsingArrowFunctions() {
  fetch('/data').then(response => response.text()).then((greeting) => {
    document.getElementById('greeting-container').innerHTML = greeting;
  });
}

/**
 * uses the async and await keywords to shorten the above code
 */
async function getGreetingUsingAsyncAwait() {
  const response = await fetch('/data');
  const greeting = await response.text();
  document.getElementById('greeting-container').innerHTML = greeting;
}
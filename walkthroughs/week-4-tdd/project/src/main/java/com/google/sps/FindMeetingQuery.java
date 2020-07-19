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

package com.google.sps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.HashSet;
import com.google.sps.TimeRange;
import com.google.sps.Event;
import com.google.sps.MeetingRequest;

public final class FindMeetingQuery {
  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
    //throw new UnsupportedOperationException("TODO: Implement this method.");
    int[] busyTime = new int[24 * 60 + 5];
    int[] pre = new int[24 * 60 + 5];
    long requestDuration = request.getDuration();
    Collection<TimeRange> available = new ArrayList<>(Collections.emptyList());
    for (Event e : events){
      Collection<String> intersection = new HashSet<>(e.getAttendees());
      intersection.retainAll(request.getAttendees());
      if (intersection.size() > 0) {
        int beginTime = e.getWhen().start();
        int endTime = e.getWhen().end();
        busyTime[beginTime] ++;
        busyTime[endTime + 1] --;
      }
    }
    for (int i = 1; i <= 24 * 60; i = i + 1) {
      busyTime[i] = busyTime[i] + busyTime[i - 1];
    }
    int beginTime = 0;
    while (beginTime < 24 * 60) {
      int duration = 0;
      for (duration = 1; beginTime + duration < 24 * 60; duration = duration + 1)
        if (busyTime[beginTime + duration] > 0){
          break;
        }
        
      if ((long)duration >= requestDuration){
        TimeRange availableSchedule = TimeRange.fromStartDuration(beginTime, duration);
        available.add(availableSchedule);
      }
      
      beginTime += duration;
    }
    return available;
  }
}

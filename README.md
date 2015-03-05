Blackboard Gradebook REST Web Service Building Block
===================

This building block provides easy access to the Blackboard Gradebook via a REST
  Web Service.

**Currently under development.  More documentation will be added later.**


Web Service Usage
-----------

All request paths must begin with

`https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/`

`BBLEARN` may be replaced with `bb_bb60` if your Bb installation has
been upgraded from an old (pre 9.1) version.


Actions
-----

The following actions are available:


### Get All Courses

**GET** `/courses`

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses
````

### Get a single Course

**GET** `/courses/{courseId}`
* `courseId` is the value stored in the `COURSE_ID` column in the DB.

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses/my_test_course
````


### Get all Assessments for a course

**GET** `/courses/{courseId}/gradebook/assessments`
* `courseId` is the value stored in the `COURSE_ID` column in the DB.

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses/my_test_course/gradebook/assessments
````
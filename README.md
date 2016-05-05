Blackboard Gradebook REST Web Service B2
===================

This building block provides easy access to the Blackboard Gradebook via a REST
  Web Service.

**Currently under development. YMMV**

Building
----------
To build this project, clone the project to a local directory ````cd```` to the directory, then run the following command for unix/osx.
````
./gradlew build
````
or, for windows.
````
gradlew.bat build
````

You can find the building block war file in the ````build/libs```` directory.


Web Service Usage
-----------

All request paths must begin with

`https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/`

`BBLEARN` may be replaced with `bb_bb60` if your Bb installation has
been upgraded from an old (pre 9.1) version.


Authentication
--------------
All requests require credentials passed using HTTP Basic Auth. In curl this would look
like the following.
````
curl -u username:password https://yourbbinstall.edu/blah/blah/blah
````

**Basic Auth credentials are *NOT* encrypted. Make sure all your requests are
performed over https**

Before you start using the WebService, you must configure a username and password
on the Building Block settings page.


Actions
-----

The following actions are available:


### Get All Courses
*If you have a lot of courses, think carefully before calling this*

**GET** `/courses`

#### curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses
````

#### Example Response
Example truncated for clarity.

```` json
[
    {
        "children": null,
        "courseId": "MECM10005_2015_SM1_DOO_1",
        "externalId": "MECM10005_2015_SM1_DOO_1",
        "id": "_26_1",
        "title": "Academic Writing and Communication"
    },
    {
        "children": null,
        "courseId": "MECM10005_2015_SM2_DOO_1",
        "externalId": "MECM10005_2015_SM2_DOO_1",
        "id": "_27_1",
        "title": "Academic Writing and Communication"
    }

    ...

}
````

### Get a single Course

**GET** `/courses/{courseId}`
* `courseId` is the value stored in the `COURSE_ID` column in the DB.

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses/my_test_course
````
#### Example Response

```` json
{
    "children": [
        {
            "children": null,
            "courseId": "2016_161_ECON77-102_RBNA_67893",
            "externalId": "2016_161_LAWS77-102_RBNA_67893",
            "id": "_57_1",
            "title": "Foundations of Economics"
        },
        {
            "children": null,
            "courseId": "2016_161_ECON10-102_RBNA_67892",
            "externalId": "2016_161_LAWS10-102_RBNA_67892",
            "id": "_56_1",
            "title": "Foundations of Economics"
        }
    ],
    "courseId": "2016_161_ECON13-102_RBNA_67891",
    "externalId": "2016_161_LAWS13-102_RBNA_67891",
    "id": "_55_1",
    "title": "Foundations of Economics"
}


````

### Get all Assessments for a course

**GET** `/courses/{courseId}/gradebook/assessments`
* `courseId` is the value stored in the `COURSE_ID` column in the DB.

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses/my_test_course/gradebook/assessments
````
#### Example Response

```` json
[
    {
        "id": "_110_1",
        "internalName": "Weighted Total",
        "name": null,
        "pointsPossible": 0.0,
        "userCreatedAssessment": false,
        "valueType": "PERCENT"
    },
    {
        "id": "_109_1",
        "internalName": "Total",
        "name": null,
        "pointsPossible": 0.0,
        "userCreatedAssessment": false,
        "valueType": "NUMBER"
    },
    {
        "id": "_115_1",
        "internalName": "Assignment One",
        "name": "Assignment One",
        "pointsPossible": 25.0,
        "userCreatedAssessment": true,
        "valueType": "NUMBER"
    }
}
````
### Get a single Assessment for a course

**GET** `/courses/{courseId}/gradebook/assessments/{assessmentId}`
* `courseId` is the value stored in the `COURSE_ID` column in the DB.
* `assessmentId` is a Bb PkId of the form `_123_1`

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses/my_test_course/gradebook/assessments/_123_1
````

#### Example Response

```` json
{
    "id": "_117_1",
    "internalName": "Final Exam",
    "name": "Final Exam",
    "pointsPossible": 50.0,
    "userCreatedAssessment": true,
    "valueType": "NUMBER"
}
````

### Get all Marks for a single Assessment for a course

**GET** `/courses/{courseId}/gradebook/assessments/{assessmentId}/marks`
* `courseId` is the value stored in the `COURSE_ID` column in the DB.
* `assessmentId` is the Bb PkId for the Assessment. It has the form `_123_1`

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/courses/my_test_course/gradebook/assessments/_123_1/marks
````


### Get all Courses for a User

**GET** `/users/{username}/courses`
* `username` is the value stored in the `USER_ID` column in the DB.

curl Example
```` shell
curl https://yourbbinstall.edu/webapps/atd-bbws-BBLEARN/ws/users/administrator/courses
````

#### Example Response

```` json
[
  {
    "id": "_202_1",
    "courseId": "2016_161_ECON77-102_RBNA_67893",
    "externalId": "2016_161_LAWS77-102_RBNA_67893",
    "title": "Foundations of Economics",
    "children": null
  },
  {
    "id": "_224_1",
    "courseId": "2016_161_ENGL31-102_GWAB_14239",
    "externalId": "2016_161_ENGL31-102_GWAB_14239",
    "title": "Academic Writing and Communication",
    "children": null
  }
]
````
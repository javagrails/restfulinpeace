# RESTful in Peace
REST api Example Using SpringBoot Version = '1.5.4.RELEASE', MySql, swagger-ui, swagger2
<br/> Just need to configure database username, password and url according to your database server in application.properties file
<br/> Then run application you will get every thing (CRUD) ready 
<br/> After run the application just enter [**http://localhost:8080/swagger-ui.html**](http://localhost:8080/swagger-ui.html) in browser address, you will get all resources documentation and test interface by the help of swagger-ui, swagger2
 
![Resources](https://github.com/javagrails/restfulinpeace/blob/master/docs/resources-structure.png)



# Resources URL
#### <i class="icon-file"></i> Controller: doctor

| Http Method | Resource URL | Rest Controller Action |
| ------- | ------- | ------- |
| GET | http://localhost:8080/doctors | Action: list |
| GET | http://localhost:8080/doctors/${id} | Action: single |
| POST | http://localhost:8080/doctors | Action: save |
| PUT | http://localhost:8080/doctors/${id} | Action: update |
| DELETE | http://localhost:8080/doctors/${id} | Action: delete |

#### <i class="icon-file"></i> Controller: patient 

| Http Method | Resource URL | Rest Controller Action |
| ------- | ------- | ------- |
| GET | http://localhost:8080/doctors/${doctorId}/patients | Action: list |
| GET | http://localhost:8080/doctors/${doctorId}/patients/${id} | Action: single |
| POST | http://localhost:8080/doctors/${doctorId}/patients | Action: save |
| PUT | http://localhost:8080/doctors/${doctorId}/patients/${id}  | Action: update |
| DELETE | http://localhost:8080/doctors/${doctorId}/patients/${id}  | Action: delete |


### Doctor POST and PUT example
Headers { Content-Type : application/json, Accept : application/json } both for POST and PUT request

POST Resource URL => http://localhost:8080/doctors

Body Content JSON 
```javascript
{
  "name": "Salman Saleh",
  "hospitalName": "Dhaka Medical College",
  "visitFee": 490
}
```

PUT Resource URL => http://localhost:8080/doctors/1

Body Content JSON 
```javascript
{
  "id":1 ,
  "hospitalName": "Dhaka Medical College Update",
  "visitFee": 495
}
```  
 

### Patient POST and PUT example
Headers { Content-Type : application/json, Accept : application/json } both for POST and PUT request

POST Resource URL => http://localhost:8080/doctors/1/patients

Body Content JSON 
```javascript
{
  "name": "Ali",
  "phone": "130465",
  "city": "Dhaka"
}

// this patient is tagged with Doctor having id 1 seen from url
```


PUT Resource URL => http://localhost:8080/doctors/1/patients/1

Body Content JSON 
```javascript
{
  "id":1,
  "phone": "65456",
  "city": "Move from Dhaka to Tangail"
}

// this patient is tagged with Doctor having id 1 seen from url
```


# This Project represents the application used for a PTI Service.
___

## There are three types of users: SECRETARY, MECHANIC and ADMIN   

___
## SECRETARY Functionalities
* Program Cars for PTI Inspection
* CRUD on appointments
* CRUD on cars
* CRUD on clients
* Program cars based on mechanic availability (multiple hired mechanics)
* Send notification to mechanic when a car has arrived for the inspection (WebSocket communication)
* After the car has been finished, view details of appointment, set price and generate receipt (download) and also send receipt via email

___
## MECHANIC Functionalities
* View cars that have appointments on current day
* Add details about PTI inspection
* View car PTI history
* Send email to client when car is finished
* Get notification from the secretary when car has arrived for inspection (WebSocket communication)

___
## ADMIN Functionalities
* CRUD on Users
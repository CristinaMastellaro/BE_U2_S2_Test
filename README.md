EndPoints
Dipendenti:
GET http://localhost:8888/employees
POST http://localhost:8888/employees
GET http://localhost:8888/employees/:employeeId
PUT http://localhost:8888/employees/:employeeId
DELETE http://localhost:8888/employees/:employeeId
PATCH http://localhost:8888/employees/:employeeId/picture > cambiare solo il profilo

Viaggi:
GET http://localhost:8888/travels
POST http://localhost:8888/travels
GET http://localhost:8888/travels/:travelId
PUT http://localhost:8888/travels/:travelId
DELETE http://localhost:8888/travels/:travelId
PATCH http://localhost:8888/travels/:travelId/employee > cambiare solo il dipendente
PATCH http://localhost:8888/travels/:travelId/state > cambiare solo lo stato

Prenotazioni:
GET http://localhost:8888/bookings
POST http://localhost:8888/bookings
GET http://localhost:8888/bookings/:bookingId
PUT http://localhost:8888/bookings/:bookingId
DELETE http://localhost:8888/bookings/:bookingId

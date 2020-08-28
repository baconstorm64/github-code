create table "Driver"
(
    id serial not null
        constraint driver_pk
            primary key,
    firstName varchar not null,
    lastName varchar not null,
    phone varchar not null,
    licenseNumber varchar not null
);

create table "Passenger"
(
    id serial not null
        constraint passenger_pk
            primary key,
    firstName varchar not null,
    lastName varchar not null,
    phone varchar not null
);

create table "State"
(
    abbreviation varchar not null
        constraint state_pk
            primary key,
    name varchar not null
);

create table "Location"
(
    id serial not null
		constraint location_pk
			primary key,
	name varchar not null,
	address varchar not null,
	city varchar not null,
	state varchar not null
		constraint location_fk
			references "State",
	"zipCode" varchar not null
);

create table "Vehicle Type"
(
    id serial not null
        constraint vehicle_type_pk
            primary key,
    type varchar not null
);

create table "Vehicle"
(
    id serial not null
        constraint vehicle_pk
            primary key,
    make varchar not null,
    model varchar not null,
    colour varchar not null,
    vehicleTypeId int not null
        constraint vehicle_fk
            references "Vehicle Type",
    capacity int not null,
    mpg float not null,
    licenseState varchar not null,
    licenseNumber varchar not null
);

create table "Authorization"
(
    driverId int not null
        constraint authorization_fk1
            references "Driver",
    vehicleId int not null
        constraint authorization_fk2
            references "Vehicle"
);

create table "Ride"
(
    id serial not null
        constraint ride_pk
            primary key,
    date date not null,
    time time not null,
    distance float not null,
    fuelPrice float not null,
    fee float not null,
    vehicleId int not null
        constraint ride_fk1
            references "Vehicle",
    fromLocationId int not null
        constraint ride_fk2
            references "Location",
    toLocationId int not null
        constraint ride_fk3
            references "Location"
);

-- This table and Passengers apparently required 2 primary keys according to the ERD
-- I'm not sure what the "unique" does, but that allows it to have 2 primary keys (I think)
create table "Drivers"
(
	"driverId" int not null
		constraint driver_pk
			unique
		constraint passengers_fk1
			references "Driver",
	"rideId" int not null
		constraint passengers_pk2
			primary key
		constraint passengers_fk2
			references "Ride"
);

create table "Passengers"
(
	"passengerId" int not null
		constraint passengers_pk
			unique
		constraint passengers_fk1
			references "Passenger",
	"rideId" int not null
		constraint passengers_pk2
			primary key
		constraint passengers_fk2
			references "Ride"
);
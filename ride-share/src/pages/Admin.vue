<template>
    <v-container>
        <v-btn text v-bind:to="{ name: 'vehicles' }">
            Add a vehicle
        </v-btn>

        <v-btn text v-bind:to="{ name: 'vehicleupdate' }">
            Update a vehicle
        </v-btn>

        <v-btn text v-bind:to="{ name: 'vehicletypes' }">
            Add a vehicle type
        </v-btn>

        <v-btn text v-bind:to="{ name: 'rides' }">
            Add a new ride (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'rideupdate'} ">
            Update a ride (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'vehicleride' }">
            Assign vehicle to ride (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'vehiclerideupdate' }">
            Update vehicle to ride (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'vehicleauthorize' }">
            Authorize driver for a vehicle (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'ridereport' }">
            View ride report
        </v-btn>

        <v-divider></v-divider>
    </v-container>
</template>
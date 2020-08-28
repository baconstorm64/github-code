<template>
    <v-container>
        <v-btn text v-bind:to="{ name: 'driveradd' }">
            Apply as a driver
        </v-btn>

        <v-btn text v-bind:to="{ name: 'rideelect'}">
            Elect to drive for a ride (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'drivereport' }">
            Get report for upcoming drives (work in progress)
        </v-btn>
    </v-container>
</template>
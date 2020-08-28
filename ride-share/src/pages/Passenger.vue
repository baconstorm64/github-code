<template>
    <v-container>
        <v-btn text v-bind:to="{ name: 'passengeradd' }">
            Apply as a passenger
        </v-btn>

        <v-btn text v-bind:to="{ name: 'ridesignup' }">
            Sign up for a ride (work in progress)
        </v-btn>

        <v-btn text v-bind:to="{ name: 'passengerridereport' }">
            Get report for upcoming rides (work in progress)
        </v-btn>
    </v-container>
</template>
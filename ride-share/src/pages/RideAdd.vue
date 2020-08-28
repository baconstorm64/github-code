<template>
    <v-container>
        <v-form v-model="valid">
            <v-text-field
                    v-model="newRide.date"
                    label="Date of Ride">
            </v-text-field>
            <v-text-field
                    v-model="newRide.time"
                    label="Time of Ride">
            </v-text-field>
            <v-text-field
                    v-model="newRide.distance"
                    label="Distance">
            </v-text-field>
            <v-text-field
                    v-model="newRide.fuelprice"
                    label="Fuel Price">
            </v-text-field>
            <v-text-field
                    v-model="newRide.fee"
                    label="Fixed Ride Fee">
            </v-text-field>
            <v-text-field
                    v-model="newRide.vehiclelicense"
                    label="Vehicle License Plate">
            </v-text-field>
            <div>
                <v-text-field
                        v-model="newRide.fromname"
                        label="Name of From Location">
                </v-text-field>
                <v-text-field
                        v-model="newRide.fromcity"
                        label="City of From Location">
                </v-text-field>
                <v-text-field
                        v-model="newRide.fromaddress"
                        label="From Location Address">
                </v-text-field>
                <v-text-field
                        v-model="newRide.fromstate"
                        label="From Location State">
                </v-text-field>
                <v-text-field
                        v-model="newRide.fromzip"
                        label="From Location Zip Code">
                </v-text-field>
            </div>

            <div>
                <v-text-field
                        v-model="newRide.toname"
                        label="Name of To Location">
                </v-text-field>
                <v-text-field
                        v-model="newRide.tocity"
                        label="City of To Location">
                </v-text-field>
                <v-text-field
                        v-model="newRide.toaddress"
                        label="To Location Address">
                </v-text-field>

                <v-text-field
                        v-model="newRide.tostate"
                        label="To Location State">
                </v-text-field>

                <v-text-field
                        v-model="newRide.tozip"
                        label="To Location Zip Code">
                </v-text-field>
            </div>
            <v-btn v-bind:disabled="!valid" v-on:click="handleSubmit">
                Add New Ride
            </v-btn>
        </v-form>

        <div class="text-xs-center">
            <v-dialog v-model="dialogVisible" width="500">
                <v-card>
                    <v-card-title primary-title>
                        {{ dialogHeader }}
                    </v-card-title>

                    <v-card-text>
                        {{ dialogText }}
                    </v-card-text>

                    <v-divider></v-divider>

                    <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="primary" text v-on:click="hideDialog">Okay</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </div>
    </v-container>
</template>

<script>
    export default {
        name: "RidePage",
        data: function () {
            return {
                //Make sure fields in form are valid
                valid: false,


                //Data for the dialog
                dialogHeader: "<no dialogHeader>",
                dialogText: "<no dialogText>",
                dialogVisible: false,

                //Object to return all ride data
                newRide: {
                    date: "",
                    time: "",
                    distance: "",
                    fuelprice: "",
                    fee: "",
                    vehiclelicense: "",
                    fromname: "",
                    fromcity: "",
                    fromaddress: "",
                    fromstate: "",
                    fromzip: "",
                    toname: "",
                    tocity: "",
                    toaddress: "",
                    tostate: "",
                    tozip: ""

                },
            }
        },
        methods: {
            handleSubmit: function () {
                this.rideCreated = false;

                this.showDialog("Sorry", "This function is not yet implemented ")
            },

            showDialog: function (header, text){
                this.dialogHeader = header;
                this.dialogText = text;
                this.dialogVisible = true;
            },

            hideDialog: function() {
                this.dialogVisible = false;
                if(this.rideCreated){
                    this.$router.push({ name: "admin" });
                }
            },
        },
    };
</script>
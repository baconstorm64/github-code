<template>
    <v-container>
        <div>
            <h4 class="display-1">Add vehicle</h4>

            <v-form v-model="valid">
                <v-text-field
                    v-model="newVehicle.make"
                    label="Vehicle Make">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.model"
                    label="Vehicle Model">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.colour"
                    label="Vehicle Colour">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.vehicletypeid"
                    label="Vehicle Type">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.capacity"
                    label="Vehicle Capacity">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.mpg"
                    label="Vehicle MPG">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.licenseState"
                    label="Vehicle License State">
                </v-text-field>
                <v-text-field
                    v-model="newVehicle.licenseNumber"
                    label="License Plate">
                </v-text-field>
                <v-btn v-bind:disabled="!valid" v-on:click="handleSubmit">
                    Add Vehicle
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
        </div>
    </v-container>
</template>

<script>
    export default {
        name: "VehiclePage",
        data: function () {
            return {
                //Make sure fields in form are valid
                valid: false,


                //Data for the dialog
                dialogHeader: "<no dialogHeader>",
                dialogText: "<no dialogText>",
                dialogVisible: false,

                //Object to return all vehicle data
                newVehicle: {
                    make: "",
                    model: "",
                    colour: "",
                    vehicletypeid: "",
                    capacity: "",
                    mpg: "",
                    licenseState: "",
                    licenseNumber: ""

                },

                //Check to see if a vehicle was successfully added
                vehicleCreated: false
            }
        },
        methods: {
            handleSubmit: function () {
                this.vehicleCreated = false;

                this.$axios
                .post("/vehicles", {
                    make: this.newVehicle.make,
                    model: this.newVehicle.model,
                    colour: this.newVehicle.colour,
                    vehicletypeid: this.newVehicle.vehicletypeid,
                    capacity: this.newVehicle.capacity,
                    mpg: this.newVehicle.mpg,
                    licenseState: this.newVehicle.licenseState,
                    licenseNumber: this.newVehicle.licenseNumber
                })
                .then((result) => {
                    if (result.data.ok){
                        this.vehicleCreated = true;
                        this.showDialog("Success", result.data.msge);
                    }
                    else{
                        this.showDialog("Failure", result.data.msge);
                    }
                })
                .catch((err) => console.log(`Error: ${err}`));
            },

            showDialog: function (header, text){
                this.dialogHeader = header;
                this.dialogText = text;
                this.dialogVisible = true;
            },

            hideDialog: function() {
                this.dialogVisible = false;
                if(this.vehicleCreated){
                    this.$router.push({ name: "admin" });
                }
            },
        },
    };
</script>
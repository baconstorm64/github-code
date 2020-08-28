<template>
    <v-container>
        <div>
            <h4 class="display-1">Add vehicle type</h4>
        </div>
        <v-form v-model="valid">
            <v-text-field
                v-model="newVehicleType.type"
                label="Vehicle Type">
            </v-text-field>

            <v-btn v-bind:disabled="!valid" v-on:click="handleSubmit">
                Add vehicle type
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
        name: "VehicleTypePage",
        data: function() {
            return {
                //Make sure fields in form are valid
                valid: false,


                //Data for the dialog
                dialogHeader: "<no dialogHeader>",
                dialogText: "<no dialogText>",
                dialogVisible: false,


                //Object to return the vehicle type
                newVehicleType: {
                    type: ""
                },

                //Check to see if a vehicle was successfully added
                vehicleTypeCreated: false
            }
        },
        methods: {
            handleSubmit: function () {
                this.vehicleTypeCreated = false;

                this.$axios
                    .post("/vehicletypes", {
                        type: this.newVehicleType.type,
                    })
                    .then((result) => {
                        if (result.data.ok) {
                            this.vehicleTypeCreated = true;
                            this.showDialog("Success", result.data.msge);
                        } else {
                            this.showDialog("Failure", result.data.msge);
                        }
                    })
                    .catch((err) => console.log(`Error: ${err}`));
            },

            showDialog: function (header, text) {
                this.dialogHeader = header;
                this.dialogText = text;
                this.dialogVisible = true;
            },

            hideDialog: function () {
                this.dialogVisible = false;
                if (this.vehicleTypeCreated) {
                    this.$router.push({name: "admin"});
                }
            },
        }
    }
</script>
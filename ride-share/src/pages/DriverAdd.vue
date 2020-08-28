<template>
    <v-container>
        <div>
            <h4 class="display-1">Apply as a driver</h4>
        </div>
        <v-form v-model="valid">
            <v-text-field
                    v-model="newDriver.firstname"
                    label="First Name">
            </v-text-field>
            <v-text-field
                    v-model="newDriver.lastname"
                    label="Last Name">
            </v-text-field>

            <v-text-field
                    v-model="newDriver.phone"
                    label="Phone Number">
            </v-text-field>

            <v-text-field
                    v-model="newDriver.licensenumber"
                    label="License Number">
            </v-text-field>

            <v-btn v-bind:disabled="!valid" v-on:click="handleSubmit">
                Apply for driver position
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
        name: "DriverPage",
        data: function() {
            return {
                //Make sure fields in form are valid
                valid: false,


                //Data for the dialog
                dialogHeader: "<no dialogHeader>",
                dialogText: "<no dialogText>",
                dialogVisible: false,


                //Object to return the vehicle type
                newDriver: {
                    firstname: "",
                    lastname: "",
                    phone: "",
                    licensenumber: "",
                },

                //Check to see if a vehicle was successfully added
                driverCreated: false
            }
        },
        methods: {
            handleSubmit: function () {
                this.driverCreated = false;

                this.$axios
                    .post("/driveradd", {
                        firstname: this.newDriver.firstname,
                        lastname: this.newDriver.lastname,
                        phone: this.newDriver.phone,
                        licensenumber: this.newDriver.licensenumber
                    })
                    .then((result) => {
                        if (result.data.ok) {
                            this.driverCreated = true;
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
                if (this.driverCreated) {
                    this.$router.push({name: "drivers"});
                }
            },
        }
    }
</script>
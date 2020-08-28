<template>
    <v-container>
        <div>
            <h4 class="display-1">Apply as a passenger</h4>
        </div>
        <v-form v-model="valid">
            <v-text-field
                    v-model="newPassenger.firstname"
                    label="First Name">
            </v-text-field>
            <v-text-field
                    v-model="newPassenger.lastname"
                    label="Last Name">
            </v-text-field>

            <v-text-field
                    v-model="newPassenger.phone"
                    label="Phone Number">
            </v-text-field>


            <v-btn v-bind:disabled="!valid" v-on:click="handleSubmit">
                Apply as passenger
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
        name: "PassengerPage",
        data: function() {
            return {
                //Make sure fields in form are valid
                valid: false,


                //Data for the dialog
                dialogHeader: "<no dialogHeader>",
                dialogText: "<no dialogText>",
                dialogVisible: false,


                //Object to return the vehicle type
                newPassenger: {
                    firstname: "",
                    lastname: "",
                    phone: "",
                },

                //Check to see if a vehicle was successfully added
                passengerCreated: false
            }
        },
        methods: {
            handleSubmit: function () {
                this.passengerCreated = false;

                this.$axios
                    .post("/passengeradd", {
                        firstname: this.newPassenger.firstname,
                        lastname: this.newPassenger.lastname,
                        phone: this.newPassenger.phone,
                    })
                    .then((result) => {
                        if (result.data.ok) {
                            this.passengerCreated = true;
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
                if (this.passengerCreated) {
                    this.$router.push({name: "passengers"});
                }
            },
        }
    }
</script>
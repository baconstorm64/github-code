<template>
    <v-container>
        <v-form v-model="valid">
            <v-text-field
                    v-model="rideElect.licensenumber"
                    label="Driver's License">
            </v-text-field>
            <v-text-field
                    v-model="rideElect.id"
                    label="Ride ID">
            </v-text-field>
            <v-btn v-bind:disabled="!valid" v-on:click="handleSubmit">
                Elect to drive
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
        name: "RideElectPage",
        data: function () {
            return {
                //Make sure fields in form are valid
                valid: false,


                //Data for the dialog
                dialogHeader: "<no dialogHeader>",
                dialogText: "<no dialogText>",
                dialogVisible: false,

                //Object to return all ride data
                rideElect: {
                    licensenumber: "",
                    id: ""
                },
            }
        },
        methods: {
            handleSubmit: function () {
                this.vehicleAuthorized = false;

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
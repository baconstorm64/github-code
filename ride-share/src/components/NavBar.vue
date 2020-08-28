<template>
	<v-app-bar app dark color="primary">
		<router-link v-bind:to="{ name: 'home-page' }">
			<v-toolbar-title class="white--text">
				Ride Share
			</v-toolbar-title>
		</router-link>

		<v-spacer></v-spacer>

		<v-btn text v-bind:to="{name: 'admin' }">
			Admin
		</v-btn>

		<v-btn text v-bind:to="{name: 'drivers' }">
			Drivers
		</v-btn>

		<v-btn text v-bind:to="{name: 'passengers' }">
			Passengers
		</v-btn>

	</v-app-bar>
</template>

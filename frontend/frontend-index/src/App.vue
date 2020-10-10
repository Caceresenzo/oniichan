<template>
	<v-app id="app">
		<v-app-bar app>
			<v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
			<v-toolbar-title>Application</v-toolbar-title>
			<v-spacer />
			<v-menu top>
				<template v-slot:activator="{ on, attrs }">
					<v-btn color="primary" dark v-bind="attrs" v-on="on">
						{{ ($auth.profile() || {}).name }}
						<v-icon right dark>
							mdi-account
						</v-icon>
					</v-btn>
				</template>

				<v-list>
					<v-list-item @click>
						<v-list-item-title>MY ACCOUNT</v-list-item-title>
					</v-list-item>
					<v-divider />
					<v-list-item @click="$auth.logout()">
						<v-list-item-title>LOGOUT</v-list-item-title>
					</v-list-item>
				</v-list>
			</v-menu>
		</v-app-bar>
		<v-navigation-drawer v-model="drawer" fixed temporary></v-navigation-drawer>
		<v-main class="grey lighten-2">
			<v-container>
				<v-row>
					<template v-for="(service) of services">
						<v-col :key="service.url" class="mt-2" cols="12">
							<v-icon>{{ service.icon }}</v-icon>
							<strong class="ml-2">{{ service.name }}</strong>
						</v-col>

						<v-col v-for="j in 6" :key="`${service.url}::${j}`" cols="6" md="2">
							<v-card>
								<v-img :src="'https://oniichan.app/static/sparkle.gif'" class="white--text align-end" gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)" height="200px">
									<v-card-title v-text="service.name"></v-card-title>
								</v-img>
							</v-card>
						</v-col>
					</template>
				</v-row>
			</v-container>
		</v-main>
	</v-app>
</template>

<script>
export default {
	data: () => ({
		menu: false,
		drawer: false,
		services: [
			{
				name: "Drive",
				url: "drive.oniichan.app",
				icon: "mdi-file",
			},
			{
				name: "Watch",
				url: "watch.oniichan.app",
				icon: "mdi-video",
			},
			{
				name: "Torrent",
				url: "torrent.oniichan.app",
				icon: "mdi-folder-network",
			},
		],
	}),
};
</script>

<template>
	<v-app id="app" dark>
		<v-main>
			<v-container class="fill-height" fluid>
				<v-row align="center" justify="center">
					<v-col cols="12" sm="8" md="4">
						<v-alert type="error" v-show="error !== null">
							{{ error }}
						</v-alert>
						<v-tabs fixed-tabs background-color="primary" dark>
							<v-tab>LOGIN</v-tab>
							<v-tab>REGISTER</v-tab>

							<v-tab-item>
								<login-form @error="(err) => error = err" @loading="(state) => loading = state" />
							</v-tab-item>

							<v-tab-item>
								<register-form @error="(err) => error = err" @loading="(state) => loading = state" />
							</v-tab-item>
						</v-tabs>
					</v-col>
				</v-row>
				<v-overlay :value="loading">
					<v-progress-circular indeterminate size="64"></v-progress-circular>
				</v-overlay>
			</v-container>
		</v-main>
	</v-app>
</template>

<script>
import LoginForm from "@/components/LoginForm.vue";
import RegisterForm from "@/components/RegisterForm.vue";

export default {
	components: {
		LoginForm,
		RegisterForm,
	},
	data: () => ({
		loading: false,
		error: null,
	}),
	methods: {
		profile() {
			console.log(this.$auth)
			return this.$auth.profile();
		}
	}
};
</script>

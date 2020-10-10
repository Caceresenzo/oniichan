<template>
	<base-form button="Login" @submit="handleSubmit">
		<v-form>
			<v-text-field v-model="email" label="Email" name="email" type="email" prepend-icon="mdi-at"></v-text-field>
			<v-text-field v-model="password" label="Password" name="password" type="password" prepend-icon="mdi-lock"></v-text-field>
		</v-form>
	</base-form>
</template>

<script>
export default {
	name: "login-form",
	data: () => ({
		email: null,
		password: null,
	}),
	methods: {
		handleSubmit() {
			this.$emit("loading", true);

			this.$http
				.post("/auth/login", {
					email: this.email,
					password: this.password,
				})
				.then((response) => {
					this.$emit("error", null);

					this.$auth.updateAuthorization(
						response.data.payload.authorization
					);
					this.$auth.goBack();
				})
				.catch((error) =>
					this.$emit("error", error.response.data.message)
				)
				.then(() => this.$emit("loading", false));
		},
	},
	mounted() {
		this.email = this.$auth.profile()?.email;
	}
};
</script>
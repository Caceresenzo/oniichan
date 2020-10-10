<template>
	<base-form button="Register" @submit="handleSubmit">
		<v-form>
			<v-text-field v-model="name" label="Name" name="name" type="text" prepend-icon="mdi-account"></v-text-field>
			<v-text-field v-model="email" label="Email" name="email" type="email" prepend-icon="mdi-at"></v-text-field>
			<v-text-field v-model="password" label="Password" name="password" type="password" prepend-icon="mdi-lock"></v-text-field>
		</v-form>
	</base-form>
</template>

<script>
export default {
	name: "register-form",
	data: () => ({
		name: null,
		email: null,
		password: null,
	}),
	methods: {
		handleSubmit() {
			this.$emit("loading", true);

			this.$http
				.post("/auth/register", {
					name: this.name,
					email: this.email,
					password: this.password,
				})
				.then((response) => {
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
};
</script>
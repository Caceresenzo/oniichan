<template>
	<v-card>
		<v-toolbar v-if="title" color="primary" dark flat>
			<v-toolbar-title>{{ title }}</v-toolbar-title>
		</v-toolbar>
		<v-card-text>
			<slot />
		</v-card-text>
		<v-card-actions>
			<v-spacer></v-spacer>
			<v-btn color="primary" @click="submit">{{ button }}</v-btn>
			<v-spacer></v-spacer>
		</v-card-actions>
	</v-card>
</template>

<script>
export default {
	name: "base-form",
	props: {
		title: {
			type: String,
		},
		button: {
			type: String,
			required: true,
		}
	},
	methods: {
		submit() {
			this.$emit("submit");
		}
	}
};
</script>

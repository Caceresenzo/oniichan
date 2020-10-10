export default {
	domain: "oniichan.app",
	home: "https://oniichan.app",
	api: {
		domain: "api.oniichan.app",
	},
	auth: {
		domain: "auth.oniichan.app",
		redirect: (from) => document.location = 'https://auth.oniichan.app/' + (from ? `?from=${encodeURIComponent(from)}` : ""),
	}
};
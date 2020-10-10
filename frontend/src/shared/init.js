import http from './http'
import auth from './auth'

export default (vue) => {
	vue.$vuetify.theme.dark = true;

	http(vue);
	auth(vue);

	return vue;
}
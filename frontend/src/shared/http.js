import Vue from "vue";
import axios from 'axios'

export default (vue) => {
	Vue.prototype.$http = axios.create({
		baseURL: "https://api.oniichan.app"
	});
}
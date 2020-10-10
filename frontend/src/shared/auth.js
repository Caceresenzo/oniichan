import Vue from "vue";
import EventEmitter from "events";
import cookie from "cookie";
import jwtdecode from 'jwt-decode';

import conf from "./conf.js";

export default (vue) => {
	Vue.prototype.$auth = new (class {

		constructor() {
			this.cookieProfile = null;
			console.log("[auth] constructed");

			let autorization = null;
			try {
				autorization = cookie.parse(document.cookie).autorization;
				this.cookieProfile = jwtdecode(autorization);
			} catch (error) {
				console.error("Failed to parse cookie", error);
			}

			if (this.cookieProfile === null) {
				if (document.domain !== "localhost" && document.domain !== conf.auth.domain) {
					conf.auth.redirect(document.location.href);
				}
			}
		}

		updateAuthorization(autorization) {
			document.cookie = cookie.serialize('autorization', autorization, {
				domain: conf.domain
			});
		}

		logout() {
			document.cookie = "";
			conf.auth.redirect();
		}

		goBack() {
			const params = new URLSearchParams(window.location.search);

			if (params.has("from")) {
				document.location = params.get("from");
			} else {
				document.location = conf.home;
			}
		}

		profile() {
			return this.cookieProfile;
		}

		isExpired() {
			return new Date().getTime() / 1000 >= this.profile()?.expired || 0;
		}

	})();
};
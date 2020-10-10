import Vue from "vue";
import App from "./App.vue";
import upperFirst from "lodash/upperFirst";
import camelCase from "lodash/camelCase";
import store from "./store"
import router from "./router"
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import vuetify from '@/plugins/vuetify'
import "./assets/css/tailwind.css"

Vue.config.productionTip = false;

const requireComponent = require.context(
	"./components/base",
	false,
	/Base[A-Z]\w+\.(vue|js)$/
);

requireComponent.keys().forEach(fileName => {
	const componentConfig = requireComponent(fileName);
	const componentName = upperFirst(
		camelCase(
			fileName
				.split("/")
				.pop()
				.replace(/\.\w+$/, "")
		)
	);
	Vue.component(componentName, componentConfig.default || componentConfig);
});

import init from "../../src/shared/init"
init(new Vue({
	store,
	router,
	vuetify,
	render: h => h(App)
})).$mount("#app");
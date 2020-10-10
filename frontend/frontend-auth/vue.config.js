module.exports = {
	outputDir: 'target/dist',
	assetsDir: 'static',
	devServer: {
		port: 8080
	},
	chainWebpack: (config) => config
		.plugin('html')
		.tap(args => {
			args[0].title = "Auth - OniiChan";
			return args;
		})
}
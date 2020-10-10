module.exports = {
	outputDir: 'target/dist',
	assetsDir: 'static',
	devServer: {
		port: 8081
	},
	chainWebpack: (config) => config
		.plugin('html')
		.tap(args => {
			args[0].title = "Index - OniiChan";
			return args;
		})
}
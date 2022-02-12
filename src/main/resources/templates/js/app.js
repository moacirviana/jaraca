const BASE_URL = 'https://servicos.tre-am.jus.br/apiConsGerv2/elo/eleitorado';
const myAppComponent = {
	data(){
	 return {
		eleitorado: []
	  }
	},
	methods : {
		async fetchApi(){
		 NProgress.start()
	  	 await axios.get(BASE_URL)
		       .then( (response)=>{
				   this.eleitorado = response.data;
				   console.log(this.eleitorado);
				   NProgress.done()
			   })
			   .catch((error)=>{
				   console.log("ocorreu um erro");
		       })
		}
	},
	mounted(){
		this.fetchApi()
	}
 };
const myApp = Vue.createApp(myAppComponent).mount("#myapp")
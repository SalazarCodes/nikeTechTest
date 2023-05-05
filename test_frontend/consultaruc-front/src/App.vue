<template>
  <div>
    <h1>Consulta RUC</h1>
    <form @submit.prevent="consultaRuc">
      <div>
        <label for="tipo">Tipo:</label>
        <input type="text" id="tipo" v-model="tipo" disabled>
      </div>
      <div>
        <label for="ruc">RUC:</label>
        <input type="text" id="ruc" v-model="ruc">
      </div>
      <button type="submit">Consultar</button>
    </form>
    <div v-if="detalle" v-show="showDetalle">
      <h2>Detalle de la consulta:</h2>
      <p><strong>RUC:</strong> {{ detalle.ruc }}</p>
      <p><strong>Razon Social:</strong> {{ detalle.razon_social }}</p>
      <p><strong>Estado:</strong> {{ detalle.estado }}</p>
      <p><strong>Direccion:</strong> {{ detalle.direccion }}</p>
      <p><strong>Ubigeo:</strong> {{ detalle.ubigeo }}</p>
      <p><strong>Departamento:</strong> {{ detalle.departamento }}</p>
      <p><strong>Provincia:</strong> {{ detalle.provincia }}</p>
      <p><strong>Distrito:</strong> {{ detalle.distrito }}</p>
    </div>
    <div v-if="error" v-show="showError">
      <p>Error: {{ error }}</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tipo: 2,
      ruc: '',
      detalle: null,
      error: null
    };
  },
  methods: {
    async consultaRuc() {

        const response = await fetch('http://localhost:9090/consulta', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify({
            tipo: this.tipo,
            ruc: this.ruc
          })
        });

        const data = await response.json();
        this.response = data;
        this.detalle = data;
        console.log(this.detalle);
        this.showDetalle = true;
        this.showError = false;
        if (this.detalle == "NO_CONTENT"){
          this.error = "No se encontraron resultados con el RUC indicado.";
          this.showDetalle = false;
          this.showError = true;
        }
      },
    },
  
};
</script>

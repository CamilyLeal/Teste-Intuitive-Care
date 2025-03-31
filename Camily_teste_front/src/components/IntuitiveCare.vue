<template>
  <div class="container">
    <h1>Resultados dos Testes</h1>
    <div v-if="loading" class="loading">Carregando...</div>
    <div v-else class="results">
      <div class="card">
        <h2>Teste 1</h2>
        <button @click="fetchTeste1">Executar Teste 1</button>
        <pre v-if="teste1">{{ teste1 }}</pre>
      </div>
      <div class="card">
        <h2>Teste 2</h2>
        <button @click="fetchTeste2">Executar Teste 2</button>
        <pre v-if="teste2">{{ teste2 }}</pre>
      </div>
      <div class="card">
        <h2>Teste 4</h2>
        <input v-model="termoBusca" placeholder="Digite o termo(Razão social, nome fantasia ou CNPJ) para buscar" />
        <button @click="fetchTeste4">Executar Teste 4</button>
        <pre v-if="teste4">{{ teste4 }}</pre>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
      teste1: null,
      teste2: null,
      teste4: null,
      termoBusca: ''  
    };
  },
  methods: {
    async fetchTeste1() {
      try {
        const res = await fetch('http://localhost:8082/download/zip');
        if (!res.ok) throw new Error(`Erro HTTP: ${res.status}`);

        const blob = await res.blob();
        const url = URL.createObjectURL(blob);

        const a = document.createElement('a');
        a.href = url;
        a.download = 'anexos.zip'; 
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);

        this.teste1 = 'Download concluído!';
      } catch (error) {
        console.error('Erro ao buscar Teste 1:', error);
        this.teste1 = 'Erro ao baixar o arquivo';
      }
    },

    async fetchTeste2() {
      try {
        const res = await fetch('http://localhost:8082/pdf/extract');
        if (!res.ok) throw new Error(`Erro HTTP: ${res.status}`);

        const blob = await res.blob();
        const url = URL.createObjectURL(blob);

        const a = document.createElement('a');
        a.href = url;
        a.download = 'Teste_CamilyLeal.zip'; 
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);

        this.teste2 = 'Download concluído!';
      } catch (error) {
        console.error('Erro ao buscar Teste 2:', error);
        this.teste2 = 'Erro ao baixar o arquivo';
      }
    },

    async fetchTeste4() {
      this.loading = true;
      if (!this.termoBusca.trim()) {
        this.teste4 = 'Por favor, insira um termo para a busca.';
        this.loading = false;
        return;
      }

      try {
        const res = await fetch(`http://localhost:8082/operadoras/buscar?term=${this.termoBusca}`);
        if (!res.ok) throw new Error(`Erro HTTP: ${res.status}`);

        const data = await res.json();

        this.teste4 = data.join('\n'); 
        this.loading = false;
      } catch (error) {
        console.error('Erro ao buscar Teste 4:', error);
        this.teste4 = 'Erro ao buscar os resultados';
        this.loading = false;
      }
    }
  }
};
</script>

<style>
body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  margin: 0;
  padding: 0;
}
.container {
  max-width: 800px;
  margin: auto;
  padding: 20px;
  text-align: center;
}
.results {
  display: grid;
  grid-template-columns: 1fr;
  gap: 15px;
  margin-top: 20px;
}
.card {
  background: white;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}
.loading {
  font-size: 18px;
  font-weight: bold;
  color: #555;
}
button {
  padding: 10px 15px;
  font-size: 16px;
  background-color: #28a745;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 10px;
}
button:hover {
  background-color: #218838;
}
input {
  padding: 8px;
  font-size: 16px;
  margin-top: 10px;
  width: 80%;
  border: 1px solid #ccc;
  border-radius: 4px;
}
</style>

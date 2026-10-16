# Filmes Favoritos

App Android (Kotlin) para visualizar e editar filmes favoritos: nome, nota e foto.

- **View Binding** para acessar os layouts (`ActivityMainBinding`, `ItemMovieBinding`, `DialogEditMovieBinding`)
- **MVVM**: `Movie` (model) -> `MovieRepository` (data) -> `MovieViewModel` (LiveData) -> `MainActivity` / `MovieAdapter` (UI)
- **Coil** para carregar a foto a partir de uma URL
- Funcionalidades: listar, adicionar (FAB +), editar (lápis) e excluir (lixeira, com confirmação)

## Como abrir
1. Android Studio (Koala ou mais recente): File > Open > pasta `FilmesFavoritos`
2. Aguarde o Gradle Sync e rode no emulador/aparelho (minSdk 24)

Obs.: os dados ficam em memória (sobrevivem à rotação de tela, mas não ao fechar o app).

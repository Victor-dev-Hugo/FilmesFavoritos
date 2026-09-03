# 🎬 Filmes Favoritos

Aplicativo Android desenvolvido em Kotlin que exibe uma lista de filmes favoritos, permitindo ao usuário adicionar novos filmes (título e diretor) dinamicamente através de um `RecyclerView`.

## ✨ Funcionalidades

- Lista de filmes favoritos exibida em um `RecyclerView`
- Cadastro de novos filmes informando **título** e **diretor**
- Alternância entre dois tipos de `LayoutManager`:
  - `GridLayoutManager` (formato grade, 2 colunas)
  - `LinearLayoutManager` (formato lista, vertical)
- Interface construída com componentes do Material Design (`MaterialCardView`)

## 🛠️ Tecnologias

- Kotlin
- Android SDK (min 21 / target 34)
- RecyclerView
- ViewBinding
- Material Components

## 📁 Estrutura do projeto

```
app/src/main/java/com/example/filmesfavoritos/
├── Movie.kt          # Data class do filme (título, diretor)
├── MovieAdapter.kt   # Adapter do RecyclerView
└── MainActivity.kt   # Tela principal: cadastro e alternância de layout

app/src/main/res/layout/
├── activity_main.xml # Tela principal
└── item_movie.xml    # Layout de cada item da lista
```

## ▶️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/SEU_USUARIO/NOME_DO_REPO.git
   ```
2. Abra a pasta do projeto no **Android Studio**.
3. Aguarde o Gradle sincronizar as dependências.
4. Rode em um emulador ou dispositivo físico (botão **Run ▶**).

## 📸 Como usar

1. Preencha os campos **Título do filme** e **Diretor**.
2. Toque em **Adicionar** para incluir o filme na lista.
3. Toque em **Grade / Lista** para alternar a visualização entre grade e lista.

## 📄 Licença

Projeto de estudo, livre para uso e modificação.

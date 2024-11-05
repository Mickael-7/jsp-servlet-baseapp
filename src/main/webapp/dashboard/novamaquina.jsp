<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="pt-br" data-bs-theme="dark">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Criar Máquina</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="style.css" rel="stylesheet">
</head>
<body class="d-flex align-items-center py-4 bg-body-tertiary">

<main class="w-100 m-auto form-container">

    <form action="/dashboard/machine" method="post">
        <h1 class="h3 mb-3 fw-normal">Cadastrar Nova Máquina:</h1>

        <div class="form-floating mb-3">
            <input type="text" name="nome" class="form-control" id="nome" placeholder="Nome da Máquina" required>
            <label for="nome">Nome da Máquina</label>
        </div>

        <div class="form-floating mb-3">
            <input type="number" step="0.01" name="pesoTotal" class="form-control" id="pesoTotal" placeholder="Peso Total" required>
            <label for="pesoTotal">Peso Total (kg)</label>
        </div>

        <div class="form-check mb-3">
            <input type="checkbox" name="quebrada" class="form-check-input" id="quebrada">
            <label class="form-check-label" for="quebrada">Está Quebrada?</label>
        </div>

        <button class="btn btn-primary w-100 py-2 mt-2" type="submit">Salvar Máquina</button>
    </form>
</main>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>

# Script para subir alterações para o GitHub
# Execute no PowerShell: .\git_push.ps1

Write-Host "🚀 Iniciando push para GitHub..." -ForegroundColor Green
Write-Host ""

# Navegar para o diretório do projeto
Set-Location "C:\Users\nicolas\java\ProjeoPets"

# Verificar status
Write-Host "📋 Status atual:" -ForegroundColor Yellow
git status
Write-Host ""

# Adicionar todos os arquivos
Write-Host "➕ Adicionando arquivos..." -ForegroundColor Yellow
git add .

# Criar commit
Write-Host "💾 Criando commit..." -ForegroundColor Yellow
git commit -m "fix: simplificar README e corrigir configurações backend

- Remover excesso de emojis e tom marketeiro do README
- Tornar documentação mais profissional e objetiva
- Corrigir porta backend (9090) e endpoints da API
- Adicionar configuração JPA e CORS
- Renomear Index.html para index.html
- Atualizar informações de execução"

Write-Host ""

# Verificar remote
Write-Host "🔗 Remote configurado:" -ForegroundColor Yellow
git remote -v
Write-Host ""

# Fazer push
Write-Host "⬆️ Fazendo push..." -ForegroundColor Yellow
git push origin main

Write-Host ""
Write-Host "✅ Concluído! Verifique em: https://github.com/XXnicor/PetRegistry" -ForegroundColor Green


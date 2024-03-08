@echo off
set MSYS2_URL=https://repo.msys2.org/distrib/x86_64/msys2-x86_64-20240113.exe
set MSYS2_INSTALLER=msys2_installer.exe

echo Baixando o instalador do MSYS2...
curl -L -o %MSYS2_INSTALLER% %MSYS2_URL%
IF %ERRORLEVEL% NEQ 0 (
    echo Falha ao baixar o instalador do MSYS2.
    exit /b 1
)

echo Instalando o MSYS2...
start /wait "" %MSYS2_INSTALLER% -q --no-startmenu --no-desktop --no-admin
IF %ERRORLEVEL% NEQ 0 (
    echo Falha ao instalar o MSYS2.
    exit /b 1
)

echo Atualizando os pacotes do MSYS2...
call C:\msys64\usr\bin\bash.exe -lc "pacman -Syuu --noconfirm"
IF %ERRORLEVEL% NEQ 0 (
    echo Falha ao atualizar os pacotes do MSYS2.
    exit /b 1
)

echo Instalando o Make...
call C:\msys64\usr\bin\bash.exe -lc "pacman -S --noconfirm make"
IF %ERRORLEVEL% NEQ 0 (
    echo Falha ao instalar o Make.
    exit /b 1
)

echo Removendo o instalador do MSYS2...
del %MSYS2_INSTALLER%

echo Instalação concluída.
pause

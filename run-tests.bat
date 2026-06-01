@echo off
cd /d "%~dp0"
echo Project folder: %CD%
git pull origin main
call mvn clean test -Dheadless=true -Dexplicit.wait=30
echo Exit code: %ERRORLEVEL%
pause

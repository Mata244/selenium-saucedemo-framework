# Finish publishing (Mata244)

Local Git is ready: commit created, remote `origin` points to your repo.

## Step 1 — Create the empty repo on GitHub

1. Sign in at https://github.com as **Mata244**
2. Open: **https://github.com/new?name=selenium-saucedemo-framework&description=Selenium+4+%2B+Java+%2B+TestNG+framework+for+SauceDemo**
3. Choose **Public**
4. Leave **unchecked**: Add README, .gitignore, license
5. Click **Create repository**

## Step 2 — Push your code

In PowerShell:

```powershell
cd "d:\Automation\Framework"
git push -u origin main
```

- If Git asks to sign in, use your GitHub account in the browser, or a [Personal Access Token](https://github.com/settings/tokens) (classic, `repo` scope) as the password.

**Optional (GitHub CLI):** After `gh auth login`, you can create and push in one step:

```powershell
gh repo create selenium-saucedemo-framework --public --source=. --remote=origin --push
```

## Step 3 — Confirm CI

1. Open https://github.com/Mata244/selenium-saucedemo-framework/actions
2. Wait for **Maven CI** to finish with a green checkmark

## One-time Git identity (recommended)

```powershell
git config --global user.name "Your Name"
git config --global user.email "your-email@example.com"
```

Use the same email as your GitHub account.

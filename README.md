# TDaily - Claim Your Daily Rewards!

Welcome to TDaily, a lightweight and user-friendly Paper plugin that brings daily rewards to your server. Keep your players engaged and excited by offering them incentives to return every 24 hours. TDaily makes it easy to configure and customize rewards, messages, and permissions, making your server more engaging than ever.

## Installation

Getting started with TDaily is a breeze! Just follow these simple steps:

1. **Download**: Grab the latest version of TDaily from our [downloads page](https://modrinth.com/plugin/tdaily/versions).

2. **Installation**: Place the downloaded TDaily file into your server's "plugins" folder.

3. **Restart**: Restart your server to activate the plugin. No dependencies required!

## Configuration

TDaily puts you in control with its user-friendly configuration options. Tailor the plugin to your server's needs by customizing rewards, associated commands, and permissions. You can even adjust the messages players receive. Here's a glimpse of what you can configure:

<details>
  <summary><b>Default Configuration (YML)</b></summary>

```yaml
# The commands to run when a player executes /daily
# Placeholders: <player>
commands:
  -
    name: tell1
    permission: true # Optional. Default: false
    commands:
      - "tell <player> Hello <player>"
      - "tell <player> You can run this command once a day if you have tdaily.claim.tell1"
  -
    name: tell2
    permission: false # Optional. Default: false
    commands:
      - "tell <player> Hello <player>"
      - "tell <player> You can run this command once a day without any permissions"
messages:
  claimed: "<green>You have claimed your keys for today! Check back in <yellow>24 hours</yellow> to claim them again.</green>"
  cooldown: "<red>You cannot execute this command until <hours> hours <minutes> minutes <seconds> seconds have passed since the last execution.</red>"   # Placeholders, <hours>, <minutes>, <seconds>
  no-rewards: "<red>There are no rewards for you to claim today.</red>"
```
</details>

## Permissions & Commands

**Permission: `tdaily.claim`**
- **Description**: The base /daily command allows players to claim daily rewards they have permission to.
- **Commands**: `/daily`, `/claimdaily`, `/dailyreward`, `/dailyclaim`

**Permission: `tdaily.claim.REWARD`**
- **Description**: Permissions for rewards with `permission` set to true in the config (replace "REWARD" with the reward name from the config).
- **Commands**: `/daily`, `/claimdaily`, `/dailyreward`, `/dailyclaim`

**Permission: `tdaily.reload`**
- **Description**: Reloads the plugin configuration.
- **Commands**: `/tdailyreload`, `/tdailyrl`, `/tdailyr`, `/tdailyreload`

**Permission: `tdaily.reset`**
- **Description**: Allows you to reset player(s) daily cooldown.
- **Commands**: `/resetdaily`




## Feature Requests
Feel like somethings missing? Make a feature request on the TDaily GitHub repository 

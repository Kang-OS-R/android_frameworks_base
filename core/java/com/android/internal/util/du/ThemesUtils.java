/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.internal.util.du;

import static android.os.UserHandle.USER_SYSTEM;

import android.app.UiModeManager;
import android.content.Context;
import android.content.om.IOverlayManager;
import android.content.om.OverlayInfo;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;
import android.util.Log;

public class ThemesUtils {

    public static final String[] ACCENTS = {
            // AOSP colors
            "com.android.theme.color.space", // Space
            "com.android.theme.color.purple", // Purple
            "com.android.theme.color.orchid", // Orchid
            "com.android.theme.color.ocean", // Ocean
            "com.android.theme.color.green", // Green
            "com.android.theme.color.cinnamon", // Cinnamon
            // New AOSP colors from Android 11
            "com.android.theme.color.amethyst", // Amethyst
            "com.android.theme.color.aquamarine", // Aquamarine
            "com.android.theme.color.carbon", // Carbon
            "com.android.theme.color.palette", // Palette
            "com.android.theme.color.sand", // Sand
            "com.android.theme.color.tangerine", // Tangerine
            // Custom colors
            "com.android.theme.color.amber", // Amber
            "com.android.theme.color.azureradiance", // AzureRadiance
            "com.android.theme.color.blue", // Blue
            "com.android.theme.color.bluegrey", // Blue Grey
            "com.android.theme.color.brown", // Brown
            "com.android.theme.color.cyan", // Cyan
            "com.android.theme.color.deeporange", // Deep Orange
            "com.android.theme.color.deeppurple", // Deep Purple
            "com.android.theme.color.grey", // Grey
            "com.android.theme.color.indigo", // Indigo
            "com.android.theme.color.lightblue", // Light Blue
            "com.android.theme.color.lightgreen", // Light Green
            "com.android.theme.color.lime", // Lime
            "com.android.theme.color.orange", // Orange
            "com.android.theme.color.pink", // Pink
            "com.android.theme.color.red", // Red
            "com.android.theme.color.teal", // Teal
            "com.android.theme.color.yellow", // Yellow
            // Brand colors
            "com.android.theme.color.androidonegreen", // AndroidOneGreen
            "com.android.theme.color.cocacolared", // CocaColaRed
            "com.android.theme.color.discordpurple", // DiscordPurple
            "com.android.theme.color.facebookblue", // FacebookBlue
            "com.android.theme.color.instagramcerise", // InstagramCerise
            "com.android.theme.color.jollibeecrimson", // JollibeeCrimson
            "com.android.theme.color.monsterenergygreen", // MonsterEnergyGreen
            "com.android.theme.color.nextbitmint", // NextbitMint
            "com.android.theme.color.oneplusred", // OneplusRed
            "com.android.theme.color.pepsiblue", // PepsiBlue
            "com.android.theme.color.pocophoneyellow", // PocophoneYellow
            "com.android.theme.color.razergreen", // RazerGreen
            "com.android.theme.color.samsungblue", // SamsungBlue
            "com.android.theme.color.spotifygreen", // SpotifyGreen
            "com.android.theme.color.starbucksgreen", // StarbucksGreen
            "com.android.theme.color.twitchpurple", // TwitchPurple
            "com.android.theme.color.twitterblue", // TwitterBlue
            "com.android.theme.color.xboxgreen", // XboxGreen
            "com.android.theme.color.xiaomiorange", // XiaomiOrange
    };

    public static final String[] ADAPTIVE_ICON_SHAPE = {
            "com.android.theme.icon.pebble",
            "com.android.theme.icon.vessel",
            "com.android.theme.icon.taperedrect",
            "com.android.theme.icon.teardrop",
            "com.android.theme.icon.squircle",
            "com.android.theme.icon.roundedrect",
            "com.android.theme.icon.square",
            "com.android.theme.icon.hexagon",
            "com.android.theme.icon.cylinder",
            "com.android.theme.icon.heart",
    };

   // QS Tile Styles
    public static final String[] QS_TILE_THEMES = {
            "com.android.systemui.qstile.default",
            "com.android.systemui.qstile.circletrim",
            "com.android.systemui.qstile.dualtonecircletrim",
            "com.android.systemui.qstile.squircletrim",
            "com.android.systemui.qstile.attemptmountain",
            "com.android.systemui.qstile.cosmos",
            "com.android.systemui.qstile.dottedcircle",
            "com.android.systemui.qstile.ninja",
            "com.android.systemui.qstile.pokesign",
            "com.android.systemui.qstile.wavey",
    };

    public static final String[] PITCH_BLACK = {
            "com.android.theme.pitchblack.system",
            "com.android.theme.pitchblack.systemui",
    };

    public static final String[] SOLARIZED_DARK = {
            "com.android.theme.solarizeddark.system",
            "com.android.theme.solarizeddark.systemui",
    };

    public static final String[] CHOCO_X = {
            "com.android.theme.chocox.system",
            "com.android.theme.chocox.systemui",
    };

    public static final String[] BAKED_GREEN = {
            "com.android.theme.bakedgreen.system",
            "com.android.theme.bakedgreen.systemui",
    };

    public static final String[] DARK_GREY = {
            "com.android.theme.darkgrey.system",
            "com.android.theme.darkgrey.systemui",
    };

    public static final String[] MATERIAL_OCEAN = {
            "com.android.theme.materialocean.system",
            "com.android.theme.materialocean.systemui",
    };

    public static final String[] TRANSPARENT_CLEAR = {
            "com.android.theme.transparentclear.system",
            "com.android.theme.transparentclear.systemui",
    };

    public static final String[] STATUSBAR_ICONS = {
            "com.android.theme.icon_pack.filled.android",
            "com.android.theme.icon_pack.rounded.android",
            "com.android.theme.icon_pack.circular.android",
            "com.android.theme.icon_pack.kai.android",
            "com.android.theme.icon_pack.sam.android",
            "com.android.theme.icon_pack.victor.android",
    };

    public static final String[] FONTS = {
            "com.android.theme.font.notoserifsource",
            "com.android.theme.font.aclonicasource",
            "com.android.theme.font.amarantesource",
            "com.android.theme.font.arvolato",
            "com.android.theme.font.bariolsource",
            "com.android.theme.font.cagliostrosource",
            "com.android.theme.font.circularstd",
            "com.android.theme.font.comicsanssource",
            "com.android.theme.font.comfortaa",
            "com.android.theme.font.coolstorysource",
            "com.android.theme.font.exotwo",
            "com.android.theme.font.firasans",
            "com.android.theme.font.googlesans",
            "com.android.theme.font.lgsmartgothicsource",
            "com.android.theme.font.rosemarysource",
            "com.android.theme.font.rubikrubik",
            "com.android.theme.font.samsungone",
            "com.android.theme.font.sfpro",
            "com.android.theme.font.slateforoneplus",
            "com.android.theme.font.sonysketchsource",
            "com.android.theme.font.storopia",
            "com.android.theme.font.surfersource",
            "com.android.theme.font.ubuntu",
            "com.android.theme.font.linotte",
            "com.android.theme.font.inter",
    };

    public static final String[] NAVBAR_STYLES = {
            "com.android.theme.navbar.android",
            "com.android.theme.navbar.asus",
            "com.android.theme.navbar.moto",
            "com.android.theme.navbar.nexus",
            "com.android.theme.navbar.old",
            "com.android.theme.navbar.oneplus",
            "com.android.theme.navbar.oneui",
            "com.android.theme.navbar.sammy",
            "com.android.theme.navbar.tecno",
    };

    public static final String[] QS_HEADER_THEMES = {
            "com.android.systemui.qsheader.grey",
            "com.android.systemui.qsheader.lightgrey",
            "com.android.systemui.qsheader.accent", 
            "com.android.systemui.qsheader.transparent",
    };

    public static final String[] SWITCH_STYLE = {
        "com.android.system.switch.md2", // 1
        "com.android.system.switch.oneplus", // 2
        "com.android.system.switch.telegram", // 3
        "com.android.system.switch.narrow", // 4
        "com.android.system.switch.contained", // 5
        "com.android.system.switch.retro", // 6
        "com.android.system.switch.stockish", // 6
    };

    public static final String[] PANEL_BG_STYLE = {
            "com.jrinfected.panel.batik", // 1
            "com.jrinfected.panel.kece", // 2
            "com.jrinfected.panel.outline", // 3
    };

    public static final String[] UI_RADIUS = {
            "com.gnonymous.gvisualmod.urm_r", // 1
            "com.gnonymous.gvisualmod.urm_m", // 2
            "com.gnonymous.gvisualmod.urm_l", // 3
    };
}

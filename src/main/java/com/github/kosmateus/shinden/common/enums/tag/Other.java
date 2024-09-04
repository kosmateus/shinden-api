package com.github.kosmateus.shinden.common.enums.tag;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Other implements Tag {
    ALCHEMY(450, "tags.other.alchemy"),
    AMNESIA(1901, "tags.other.amnesia"),
    BASEBALL(506, "tags.other.baseball"),
    BOXING(67, "tags.other.boxing"),
    COLD_WEAPON(2865, "tags.other.cold-weapon"),
    FIREARM(2155, "tags.other.firearm"),
    BUDDHIST(2361, "tags.other.buddhist"),
    ILLNESS(2355, "tags.other.illness"),
    CROSSDRESSING(2346, "tags.other.crossdressing"),
    DEATH_GAME(1933, "tags.other.death-game"),
    BODY_SHARING(2339, "tags.other.body-sharing"),
    EDUCATIONAL(558, "tags.other.educational"),
    ECONOMY(1763, "tags.other.economy"),
    HUMAN_EXPERIMENTATION(2354, "tags.other.human-experimentation"),
    CONTEMPORARY_FANTASY(2345, "tags.other.contemporary-fantasy"),
    PHOTOGRAPHY(2862, "tags.other.photography"),
    GUILDS(2351, "tags.other.guilds"),
    GYMNASTICS(2714, "tags.other.gymnastics"),
    GOLF(2934, "tags.other.golf"),
    GORE(2050, "tags.other.gore"),
    HIGH_STAKES(2377, "tags.other.high-stakes"),
    CARD_GAMES(1904, "tags.other.card-games"),
    GAMBLING(2350, "tags.other.gambling"),
    ISEKAI(2376, "tags.other.isekai"),
    IYASHIKEI(2358, "tags.other.iyashikei"),
    CANNIBALISM(2739, "tags.other.cannibalism"),
    INCEST(383, "tags.other.incest"),
    KENDO(554, "tags.other.kendo"),
    SCHOOL_CLUB(2765, "tags.other.school-club"),
    CYCLING(1947, "tags.other.cycling"),
    MARRIAGE_CONTRACT(2978, "tags.other.marriage-contract"),
    BASKETBALL(225, "tags.other.basketball"),
    CULINARY(1803, "tags.other.culinary"),
    AVIATION(1749, "tags.other.aviation"),
    MAFIA(513, "tags.other.mafia"),
    MAHJONG(357, "tags.other.mahjong"),
    TIME_AND_SPACE_MANIPULATION(1840, "tags.other.time-and-space-manipulation"),
    CHRISTIAN_MYTHOLOGY(2360, "tags.other.christian-mythology"),
    JAPANESE_MYTHOLOGY(2359, "tags.other.japanese-mythology"),
    ABOUT_GAME(2324, "tags.other.about-game"),
    CHILDCARE(2342, "tags.other.childcare"),
    MASTER_SERVANT_RELATIONSHIP(2770, "tags.other.master-servant-relationship"),
    PANTY_SHOTS(2365, "tags.other.panty-shots"),
    FOOTBALL(32, "tags.other.football"),
    TRAINS(2370, "tags.other.trains"),
    TIME_TRAVEL(2731, "tags.other.time-travel"),
    POLITICS(2826, "tags.other.politics"),
    VIOLENCE(1736, "tags.other.violence"),
    REINCARNATION(2367, "tags.other.reincarnation"),
    AGRICULTURE(2331, "tags.other.agriculture"),
    CARS(47, "tags.other.cars"),
    STUDENT_COUNCIL(2411, "tags.other.student-council"),
    SEX(1786, "tags.other.sex"),
    SHOGI(2824, "tags.other.shogi"),
    VOLLEYBALL(2216, "tags.other.volleyball"),
    CONSPIRACY(2344, "tags.other.conspiracy"),
    BATTLE_SUITS(3026, "tags.other.battle-suits"),
    GUNFIGHTS(2352, "tags.other.gunfights"),
    SUPER_POWER(58, "tags.other.super-power"),
    DANCE(2318, "tags.other.dance"),
    TATTOOS(2750, "tags.other.tattoos"),
    TENNIS(66, "tags.other.tennis"),
    LOVE_TRIANGLE(1743, "tags.other.love-triangle"),
    HAND_TO_HAND_COMBAT(2353, "tags.other.hand-to-hand-combat"),
    ROMANTIC_PLOT(2674, "tags.other.romantic-plot"),
    WAR(1962, "tags.other.war"),
    SEXUAL_ABUSE(2368, "tags.other.sexual-abuse"),
    EXPLICIT_SEX(2349, "tags.other.explicit-sex"),
    CAR_RACING(1903, "tags.other.car-racing"),
    YAKUZA(1089, "tags.other.yakuza"),
    ARRANGED_MARRIAGE(2337, "tags.other.arranged-marriage"),
    BODY_SWAPPING(1732, "tags.other.body-swapping"),
    REVENGE(2145, "tags.other.revenge"),
    ANIMAL_ABUSE(2334, "tags.other.animal-abuse"),
    BULLYING(2340, "tags.other.bullying"),
    AFTERLIFE(2330, "tags.other.afterlife"),
    ARCHERY(2335, "tags.other.archery"),
    SKATING(2153, "tags.other.skating");


    private final Integer id;
    private final String translationKey;

    @Override
    public String getTagType() {
        return "tag";
    }

    @Override
    public String getQueryParameter() {
        return "tag";
    }

    @Override
    public String getQueryValue() {
        return String.valueOf(id);
    }
}

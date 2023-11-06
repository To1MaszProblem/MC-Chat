package pl.to1maszproblem.configuration;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import lombok.Getter;
import org.bukkit.Material;
import pl.to1maszproblem.notice.Notice;
import pl.to1maszproblem.notice.NoticeType;

import java.util.Arrays;
import java.util.List;

@Getter
public class Configuration extends OkaeriConfig {

    @Comment(" ")
    @Comment(" * Plugin autorstwa: @To1MaszProblem - https://github.com/To1MaszProblem")
    @Comment(" ")
    @Comment(" * Permisje: [chat.bypass, chat.admin, chat.cmd]")
    @Comment(" ")
    @Comment(" * Plugin wspiera wersje: > 1.16.5")
    @Comment(" ")


    private Notice noPermission = new Notice(NoticeType.SUBTITLE, "&cNie posiadasz uprawnien do tego! &8(&4[permission]&8)");
    private Notice chatIsOn = new Notice(NoticeType.MESSAGE, "&8>> &aCzat jest  włączony");
    private Notice chatIsOff = new Notice(NoticeType.MESSAGE, "&8>> &cCzat jest  wyłączony");

    private Notice chatCancelPlayer = new Notice(NoticeType.MESSAGE, "&8>> &cCzat aktóalnie jest wyłączony!");
    private Notice chatCancelAdmin = new Notice(NoticeType.MESSAGE, "&c&lCHAT &8** &fGracz &c[player] &fpróbował napisać wiadomość, ale chat jest &4wyłączony!");

    private Notice chatOnSender = new Notice(NoticeType.MESSAGE, "&8>> &7Chat został &awłączony");
    private Notice chatOnBroadCast = new Notice(NoticeType.MESSAGE, "&8>> &7Czat został &awłączony &7przez administratora &a[player]");

    private Notice chatOffSender = new Notice(NoticeType.MESSAGE, "&8>> &7Chat został &cwyłączony");
    private Notice chatOffBroadCast = new Notice(NoticeType.MESSAGE, "&8>> &7Czat został &cwyłączony &7przez administratora &c[player]");

    private Notice chatClearSender = new Notice(NoticeType.MESSAGE, "&8>> &fPomyślnie wyczyszczono czat!");
    private Notice chatClearBroadCast = new Notice(NoticeType.MESSAGE, "&8>> &7Czat został wyczyszczony przez administratora &f[player]");

    private Material itemMessageHistoryType = Material.PAPER;
    private String HistoryMenuTitle = "&cWiadomość gracza [player]";
    private List<String> messageHistoryMenuLore = Arrays.asList(" ", " &8** &fWiadomość: &7[message] &8**", " ");
}

@file:Suppress("FunctionName")

package icu.samnyan.aqua.sega.maimai2.model

import icu.samnyan.aqua.net.games.GenericPlaylogRepo
import icu.samnyan.aqua.net.games.GenericUserDataRepo
import icu.samnyan.aqua.sega.general.model.Card
import icu.samnyan.aqua.sega.maimai2.model.userdata.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.*

@NoRepositoryBean
interface UserLinked<T>: JpaRepository<T, Long> {
    fun findByUser(user: Mai2UserDetail): List<T>
    fun findSingleByUser(user: Mai2UserDetail): Optional<T>
    fun findByUser_Card_ExtId(userId: Long): List<T>
    fun findByUser_Card_ExtId(userId: Long, page: Pageable): Page<T>
    fun findSingleByUser_Card_ExtId(userId: Long): Optional<T>
    @Transactional
    fun deleteByUser(user: Mai2UserDetail)
}

interface Mai2MapEncountNpcRepo : UserLinked<Mai2MapEncountNpc>

interface Mai2UserActRepo : UserLinked<Mai2UserAct> {
    fun findByUserAndKindAndActivityId(user: Mai2UserDetail, kind: Int, id: Int): Optional<Mai2UserAct>

    fun findByUser_Card_ExtIdAndKind(userId: Long, kind: Int): List<Mai2UserAct>
}

interface Mai2UserCardRepo : UserLinked<Mai2UserCard> {
    fun findByUserAndCardId(user: Mai2UserDetail, cardId: Int): Optional<Mai2UserCard>
}

interface Mai2UserCharacterRepo : UserLinked<Mai2UserCharacter> {
    fun findByUserAndCharacterId(user: Mai2UserDetail, characterId: Int): Optional<Mai2UserCharacter>
}

interface Mai2UserChargeRepo : UserLinked<Mai2UserCharge>

interface Mai2UserCourseRepo : UserLinked<Mai2UserCourse> {
    fun findByUserAndCourseId(user: Mai2UserDetail, courseId: Int): Optional<Mai2UserCourse>
}

interface Mai2UserDataRepo : GenericUserDataRepo<Mai2UserDetail> {
    fun findByCardExtId(userId: Long): Optional<Mai2UserDetail>

    @Transactional
    fun deleteByCard(card: Card)
}

interface Mai2UserExtendRepo : UserLinked<Mai2UserExtend>

interface Mai2UserFavoriteRepo : UserLinked<Mai2UserFavorite> {
    fun findByUserAndItemKind(user: Mai2UserDetail, kind: Int): Optional<Mai2UserFavorite>

    fun findByUserIdAndItemKind(userId: Long, kind: Int): List<Mai2UserFavorite>
}

interface Mai2UserFriendSeasonRankingRepo : UserLinked<Mai2UserFriendSeasonRanking> {
    fun findByUserAndSeasonId(user: Mai2UserDetail, seasonId: Int): Optional<Mai2UserFriendSeasonRanking>
}

interface Mai2UserGeneralDataRepo : UserLinked<Mai2UserGeneralData> {
    fun findByUserAndPropertyKey(user: Mai2UserDetail, key: String): Optional<Mai2UserGeneralData>

    fun findByUser_Card_ExtIdAndPropertyKey(userId: Long, key: String): Optional<Mai2UserGeneralData>
}

interface Mai2UserItemRepo : UserLinked<Mai2UserItem> {
    fun findByUserAndItemKindAndItemId(user: Mai2UserDetail, itemKind: Int, itemId: Int): Optional<Mai2UserItem>

    fun findByUser_Card_ExtIdAndItemKind(userId: Long, kind: Int, page: Pageable): Page<Mai2UserItem>
}

interface Mai2UserLoginBonusRepo : UserLinked<Mai2UserLoginBonus> {
    fun findByUserAndBonusId(user: Mai2UserDetail, bonusId: Int): Optional<Mai2UserLoginBonus>
}

interface Mai2UserMapRepo : UserLinked<Mai2UserMap> {
    fun findByUserAndMapId(user: Mai2UserDetail, mapId: Int): Optional<Mai2UserMap>
}

interface Mai2UserMusicDetailRepo : UserLinked<Mai2UserMusicDetail> {
    fun findByUser_Card_ExtIdAndMusicId(userId: Long, id: Int): List<Mai2UserMusicDetail>

    fun findByUserAndMusicIdAndLevel(user: Mai2UserDetail, musicId: Int, level: Int): Optional<Mai2UserMusicDetail>
}

interface Mai2UserOptionRepo : UserLinked<Mai2UserOption>

interface Mai2UserPlaylogRepo : GenericPlaylogRepo<Mai2UserPlaylog>, UserLinked<Mai2UserPlaylog> {
    fun findByUser_Card_ExtIdAndMusicIdAndLevel(userId: Long, musicId: Int, level: Int): List<Mai2UserPlaylog>
}

interface Mai2UserPrintDetailRepo : JpaRepository<Mai2UserPrintDetail, Long>

interface Mai2UserUdemaeRepo : UserLinked<Mai2UserUdemae>

interface Mai2GameChargeRepo : JpaRepository<Mai2GameCharge, Long>

interface Mai2GameEventRepo : JpaRepository<Mai2GameEvent, Int> {
    fun findByTypeAndEnable(type: Int, enable: Boolean): List<Mai2GameEvent>
}

interface Mai2GameSellingCardRepo : JpaRepository<Mai2GameSellingCard, Long>

@Component
class Mai2Repos(
    val mapEncountNpc: Mai2MapEncountNpcRepo,
    val userAct: Mai2UserActRepo,
    val userCard: Mai2UserCardRepo,
    val userCharacter: Mai2UserCharacterRepo,
    val userCharge: Mai2UserChargeRepo,
    val userCourse: Mai2UserCourseRepo,
    val userData: Mai2UserDataRepo,
    val userExtend: Mai2UserExtendRepo,
    val userFavorite: Mai2UserFavoriteRepo,
    val userFriendSeasonRanking: Mai2UserFriendSeasonRankingRepo,
    val userGeneralData: Mai2UserGeneralDataRepo,
    val userItem: Mai2UserItemRepo,
    val userLoginBonus: Mai2UserLoginBonusRepo,
    val userMap: Mai2UserMapRepo,
    val userMusicDetail: Mai2UserMusicDetailRepo,
    val userOption: Mai2UserOptionRepo,
    val userPlaylog: Mai2UserPlaylogRepo,
    val userPrintDetail: Mai2UserPrintDetailRepo,
    val userUdemae: Mai2UserUdemaeRepo,
    val gameCharge: Mai2GameChargeRepo,
    val gameEvent: Mai2GameEventRepo,
    val gameSellingCard: Mai2GameSellingCardRepo
)

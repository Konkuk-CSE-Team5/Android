package com.konkuk.hackathon.feature.volunteer.setting.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme

@Composable
fun VolunteerSettingsScreen(padding: PaddingValues, modifier: Modifier = Modifier, onClickModify : () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = OnItTheme.colors.white)
            .padding(padding)
    ) {
        Box(modifier = modifier
            .fillMaxWidth()
            .background(OnItTheme.colors.white)
            .padding(vertical = 12.dp)
            .padding(horizontal = 10.dp),) {
            Text("설정", style = OnItTheme.typography.SB_24, color = OnItTheme.colors.gray7,
                modifier = Modifier.align(Alignment.CenterStart))
        }
        Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 16.dp, bottom = 12.dp).clip(RoundedCornerShape(8.dp)).background(OnItTheme.colors.white).border(1.dp,
            OnItTheme.colors.gray2, RoundedCornerShape(8.dp)).padding(16.dp)) {
            Text("프로필", style = OnItTheme.typography.B_17, color = OnItTheme.colors.gray7)
            Spacer(modifier = Modifier.height(12.dp))
            Text("이름: 홍길동", style = OnItTheme.typography.R_14, color = OnItTheme.colors.gray7)
            Spacer(modifier = Modifier.height(8.dp))
            Text("전화번호: 010-1234-5678", style = OnItTheme.typography.R_14, color = OnItTheme.colors.gray7)
            // 추후 값 받아오는 방식으로 수정 필요
            Spacer(modifier = Modifier.height(12.dp))
            Text("정보 수정", style = OnItTheme.typography.SB_14, color = OnItTheme.colors.primary, modifier = Modifier.align(Alignment.End).clickable{onClickModify()})
        }
    }
}

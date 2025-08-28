package com.konkuk.hackathon.feature.volunteer.setting.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.hackathon.R
import com.konkuk.hackathon.core.designsystem.theme.OnItTheme
import com.konkuk.hackathon.feature.volunteer.setting.component.BirthTransformation
import com.konkuk.hackathon.feature.volunteer.setting.component.GenderButton
import com.konkuk.hackathon.feature.volunteer.setting.component.MyInfoTextField
import com.konkuk.hackathon.feature.volunteer.setting.component.PhoneTransformation
import com.konkuk.hackathon.feature.volunteer.setting.viewmodel.ProfileViewModel

@Composable
fun VolunteerInfoScreen(padding : PaddingValues, modifier: Modifier = Modifier,vm: ProfileViewModel, onBackClick: () -> Unit, onClickEdit: () -> Unit) {
   val ui by vm.ui.collectAsState()
    val ctx = LocalContext.current




    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = OnItTheme.colors.white)
            .padding(padding)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(OnItTheme.colors.white)
                .padding(vertical = 12.dp)
                .padding(start = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(24.dp)
                    .clickable(
                        onClick = onBackClick
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_big_left),
                    contentDescription = null,
                    tint = OnItTheme.colors.black
                )
            }
            Text(
                text = "프로필 수정",
                color = OnItTheme.colors.gray7,
                modifier = Modifier
                    .align(Alignment.Center),
                style = OnItTheme.typography.SB_20
            )
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp, 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                Text(
                    "아이디",
                    color = OnItTheme.colors.gray7,
                    style = OnItTheme.typography.M_17
                )
                Spacer(Modifier.height(10.dp))
                Box(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(
                            color = OnItTheme.colors.gray1,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .border(
                            color = OnItTheme.colors.gray2,
                            width = 1.2.dp,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(start = 16.dp)

                ) {
                    Text(
                        ui.id,
                        color = OnItTheme.colors.gray3,
                        style = OnItTheme.typography.M_16,
                        modifier = modifier
                            .align(
                                Alignment.CenterStart
                            )
                            .padding(vertical = 16.dp)
                    )
                }
            }
            MyInfoTextField(
                value = ui.password,
                category = "비밀번호",
                placeHolder = "비밀번호",
                onValueChange = vm::updatePassword,
                keyboardType = KeyboardType.Password,
                visualTransformation = PasswordVisualTransformation(),
                maxLength = 20
            )
            MyInfoTextField(
                value = ui.name,
                category = "이름",
                placeHolder = "이름",
                onValueChange = vm::updateName,
            )
            MyInfoTextField(
                value = ui.birthdayDigits,
                category = "생년월일",
                placeHolder = "YYYY / MM / DD",
                onValueChange = vm::updateBirthDigits,
                maxLength = 8,
                keyboardType = KeyboardType.Number,
                visualTransformation = BirthTransformation()
            )
            Column(modifier = modifier.fillMaxWidth()) {
                Text("성별", style = OnItTheme.typography.M_17, color = OnItTheme.colors.gray7)
                Spacer(modifier = modifier.height(10.dp))
                GenderButton(isMale = ui.isMale, onClick = { vm::updateGender })
            }
            MyInfoTextField(
                value = ui.phoneDigits,
                category = "전화번호",
                placeHolder = "전화번호",
                onValueChange = vm::updatePhoneDigits,
                keyboardType = KeyboardType.Phone,
                maxLength = 11,
                visualTransformation = PhoneTransformation()
            )
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(14.dp))
                    .fillMaxWidth()
                    .background(
                        color = if (ui.isSavable) OnItTheme.colors.primary else OnItTheme.colors.gray2,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .clickable(onClick = {
                        if (!ui.isSavable) return@clickable
                        vm.updateProfile() {
                            onClickEdit()
                        }
                    }
                    )
            ) {
                Text(
                    text = "수정",
                    style = OnItTheme.typography.B_17,
                    modifier = Modifier
                        .padding(vertical = 13.dp)
                        .align(Alignment.Center),
                    color = if (ui.isSavable) OnItTheme.colors.white else OnItTheme.colors.gray7
                )
            }
        }

    }
}
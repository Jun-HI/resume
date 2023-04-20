// //이벤트 타겟 설정
// const $title1 = document.querySelector('.main__title a:nth-child(1)');
// const $title2 = document.querySelector('.main__title a:nth-child(2)');
// const $personMember = document.querySelector('.main__personMember');
// const $companyMember = document.querySelector('.main__companyMember');

// //토글 기능 -> 개인회원으로 전환하는 로직
// const changPersonMember = () => {
//   if ($personMember.classList.contains('active')) {
//     $personMember.classList.toggle('active');
//     $companyMember.classList.toggle('active');
//   }
//   if ($title1.classList.contains('active')) {
//     $title2.classList.toggle('active');
//     $title1.classList.toggle('active');
//   }
// };
// //이벤트 리스너 설정
// $title1.addEventListener('click', changPersonMember);

// //토글 기능 -> 기업회원으로 전환하는 로직
// const changCompanyMember = () => {
//   if (!$companyMember.classList.contains('active')) {
//     $companyMember.classList.toggle('active');
//     $personMember.classList.toggle('active');
//     $title2.classList.toggle('active');
//     $title1.classList.toggle('active');
//   }
//   if (!$title2.classList.contains('active')) {
//     $title2.classList.toggle('active');
//     $title1.classList.toggle('active');
//   }
// };
// //이벤트 리스너 설정
// $title2.addEventListener('click', changCompanyMember);

/* 동의부분 checkbox */
{
  const $checkb = document.getElementById('checkb');
  const $chkbox = document.getElementsByName('chkbox');

  function checkAll() {
    if ($checkb.checked == true) {
      for (let i = 0; i < 6; i++) {
        $chkbox[i].checked = true;
      }
    } else {
      for (let i = 0; i < 6; i++) {
        $chkbox[i].checked = false;
      }
    }
  }
}

/* 동의부분 diplay */
{
  const $fa_down_a = document.querySelector('#mm .fa-solid.fa-sort-down.a');
  const $fa_up_a = document.querySelector('#mm .fa-solid.fa-caret-up.a');
  const $text_box1 = document.getElementById('text-box1');

  $fa_down_a.addEventListener('click', () => {
    ($fa_down_a.style.display = 'none'),
      ($fa_up_a.style.display = 'block'),
      ($text_box1.style.display = 'block');
  });
  $fa_up_a.addEventListener('click', () => {
    ($fa_down_a.style.display = 'block'),
      ($fa_up_a.style.display = 'none'),
      ($text_box1.style.display = 'none');
  });

  const $fa_down_b = document.querySelector('#mm .fa-solid.fa-sort-down.b');
  const $fa_up_b = document.querySelector('#mm .fa-solid.fa-caret-up.b');
  const $text_box2 = document.getElementById('text-box2');

  $fa_down_b.addEventListener('click', () => {
    ($fa_down_b.style.display = 'none'),
      ($fa_up_b.style.display = 'block'),
      ($text_box2.style.display = 'block');
  });
  $fa_up_b.addEventListener('click', () => {
    ($fa_down_b.style.display = 'block'),
      ($fa_up_b.style.display = 'none'),
      ($text_box2.style.display = 'none');
  });

  const $fa_down_c = document.querySelector('#mm .fa-solid.fa-sort-down.c');
  const $fa_up_c = document.querySelector('#mm .fa-solid.fa-caret-up.c');
  const $text_box3 = document.getElementById('text-box3');

  $fa_down_c.addEventListener('click', () => {
    ($fa_down_c.style.display = 'none'),
      ($fa_up_c.style.display = 'block'),
      ($text_box3.style.display = 'block');
  });
  $fa_up_c.addEventListener('click', () => {
    ($fa_down_c.style.display = 'block'),
      ($fa_up_c.style.display = 'none'),
      ($text_box3.style.display = 'none');
  });

  const $fa_down_d = document.querySelector('#mm .fa-solid.fa-sort-down.d');
  const $fa_up_d = document.querySelector('#mm .fa-solid.fa-caret-up.d');
  const $text_box4 = document.getElementById('text-box4');

  $fa_down_d.addEventListener('click', () => {
    ($fa_down_d.style.display = 'none'),
      ($fa_up_d.style.display = 'block'),
      ($text_box4.style.display = 'block');
  });
  $fa_up_d.addEventListener('click', () => {
    ($fa_down_d.style.display = 'block'),
      ($fa_up_d.style.display = 'none'),
      ($text_box4.style.display = 'none');
  });

  const $fa_down_e = document.querySelector('#mm .fa-solid.fa-sort-down.e');
  const $fa_up_e = document.querySelector('#mm .fa-solid.fa-caret-up.e');
  const $text_box5 = document.getElementById('text-box5');

  $fa_down_e.addEventListener('click', () => {
    ($fa_down_e.style.display = 'none'),
      ($fa_up_e.style.display = 'block'),
      ($text_box5.style.display = 'block');
  });
  $fa_up_e.addEventListener('click', () => {
    ($fa_down_e.style.display = 'block'),
      ($fa_up_e.style.display = 'none'),
      ($text_box5.style.display = 'none');
  });

  const $fa_down_f = document.querySelector('#mm .fa-solid.fa-sort-down.f');
  const $fa_up_f = document.querySelector('#mm .fa-solid.fa-caret-up.f');
  const $text_box6 = document.getElementById('text-box6');

  $fa_down_f.addEventListener('click', () => {
    ($fa_down_f.style.display = 'none'),
      ($fa_up_f.style.display = 'block'),
      ($text_box6.style.display = 'block');
  });
  $fa_up_f.addEventListener('click', () => {
    ($fa_down_f.style.display = 'block'),
      ($fa_up_f.style.display = 'none'),
      ($text_box6.style.display = 'none');
  });
}
{
  /* 아이디 조건 */
  const $id = document.getElementById('id');
  const $id_down = document.getElementById('id-down');
  const $check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $checkS = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/;
  $id.addEventListener('keyup', e => {
    if (
      (e.target.value.trim().length >= 4 &&
        e.target.value.trim().length <= 15 &&
        !$check.test($id.value)) ||
      e.target.value.trim().length == 0
    ) {
      $id_down.style.display = 'none';
    } else {
      $id_down.style.display = 'block';
    }
    if ($checkS.test($id.value)) {
      $id.value = $id.value.substring(0, $id.value.length - 1);
    }
  });
}

/* 비밀번호 숨김/표시 기능 */
{
  const $pass = document.getElementById('pass');
  const $lightbulb = document.querySelector('.fa-regular.fa-lightbulb.a');
  const $lightbulbx = document.querySelector('.fa-solid.fa-lightbulb.a');
  $lightbulb.addEventListener('click', () => {
    $pass.setAttribute('type', 'text');
    $pass.style.width = '450px';
    $lightbulb.style.display = 'none';
    $lightbulbx.style.display = 'inline-block';
  });
  $lightbulbx.addEventListener('click', () => {
    $pass.setAttribute('type', 'password');
    $lightbulbx.style.display = 'none';
    $lightbulb.style.display = 'inline-block';
  });
}

/* 비밀번호 재입력 숨김/표시 기능 */
{
  const $passc = document.getElementById('passc');
  const $lightbulb = document.querySelector('.fa-regular.fa-lightbulb.b');
  const $lightbulbx = document.querySelector('.fa-solid.fa-lightbulb.b');
  $lightbulb.addEventListener('click', () => {
    $passc.setAttribute('type', 'text');
    $passc.style.width = '450px';
    $lightbulb.style.display = 'none';
    $lightbulbx.style.display = 'inline-block';
  });
  $lightbulbx.addEventListener('click', () => {
    $passc.setAttribute('type', 'password');
    $lightbulbx.style.display = 'none';
    $lightbulb.style.display = 'inline-block';
  });
}

{
  /* 비밀번호 조건 */
  const $pass = document.getElementById('pass');
  const $pass_down = document.getElementById('pass-down');
  const $check = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $checkS = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/;
  $pass.addEventListener('keyup', e => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$check.test($pass.value)) ||
      e.target.value.trim().length == 0
    ) {
      $pass_down.style.display = 'none';
    } else {
      $pass_down.style.display = 'block';
    }
    if ($checkS.test($pass.value)) {
      $pass.value = $pass.value.substring(0, $pass.value.length - 1);
    }
  });
}
{
  /* 비밀번호 재입력 조건 */
  const $passc = document.getElementById('passc');
  const $passc_down = document.getElementById('passc-down');
  const $check = /[ ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $checkS = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/;
  $passc.addEventListener('keyup', e => {
    if (
      (e.target.value.trim().length >= 8 &&
        e.target.value.trim().length <= 20 &&
        !$check.test($passc.value)) ||
      e.target.value.trim().length == 0
    ) {
      $passc_down.style.display = 'none';
    } else {
      $passc_down.style.display = 'block';
    }
    if ($checkS.test($passc.value)) {
      $passc.value = $passc.value.substring(0, $passc.value.length - 1);
    }
  });
}
{
  /* 이름 조건 */
  const $name = document.getElementById('name');
  const $name_down = document.getElementById('name-down');
  const $check = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/;
  const $checkS = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/;
  $name.addEventListener('keyup', e => {
    if (
      (e.target.value.trim().length >= 2 &&
        e.target.value.trim().length <= 5 &&
        $check.test($name.value)) ||
      e.target.value.trim().length == 0
    ) {
      $name_down.style.display = 'none';
    } else {
      $name_down.style.display = 'block';
    }

    if ($checkS.test($name.value)) {
      $name.value = $name.value.substring(0, $name.value.length - 1);
    }
  });
}
{
  /* 생년월일 조건 */
  const $birth = document.getElementById('birth');
  const $birth_down = document.getElementById('birth-down');
  const $check = /[0-9]/;
  const $checkS = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/;
  $birth.addEventListener('keyup', e => {
    if (
      (e.target.value.trim().length >= 1 &&
        e.target.value.trim().length <= 8 &&
        $check.test($birth.value)) ||
      e.target.value.trim().length == 0
    ) {
      $birth_down.style.display = 'none';
    } else {
      $birth_down.style.display = 'block';
    }
    if ($checkS.test($birth.value)) {
      $birth.value = $birth.value.substring(0, $birth.value.length - 1);
    }
  });
}

/* 성별 선택 */
{
  const $male = document.getElementById('male');
  const $female = document.getElementById('female');
  const $ma = document.querySelector('#birth-b .ma');
  const $wo = document.querySelector('#birth-b .wo');

  $male.addEventListener('click', e => {
    if (e.target.checked == true) {
      $ma.style.backgroundColor = '#82F9B7';
      $wo.style.backgroundColor = 'white';
    }
  });
  $female.addEventListener('click', e => {
    if (e.target.checked == true) {
      $wo.style.backgroundColor = '#82F9B7';
      $ma.style.backgroundColor = 'white';
    }
  });
}
/* 우편번호 */
{
  function sample6_execDaumPostcode() {
    new daum.Postcode({
      oncomplete: function (data) {
        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
        var addr = ''; // 주소 변수
        var extraAddr = ''; // 참고항목 변수

        //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
        if (data.userSelectedType === 'R') {
          // 사용자가 도로명 주소를 선택했을 경우
          addr = data.roadAddress;
        } else {
          // 사용자가 지번 주소를 선택했을 경우(J)
          addr = data.jibunAddress;
        }

        // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
        if (data.userSelectedType === 'R') {
          // 법정동명이 있을 경우 추가한다. (법정리는 제외)
          // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
          if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
            extraAddr += data.bname;
          }
          // 건물명이 있고, 공동주택일 경우 추가한다.
          if (data.buildingName !== '' && data.apartment === 'Y') {
            extraAddr +=
              extraAddr !== '' ? ', ' + data.buildingName : data.buildingName;
          }
          // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
          if (extraAddr !== '') {
            extraAddr = ' (' + extraAddr + ')';
          }
          // 조합된 참고항목을 해당 필드에 넣는다.
          document.getElementById('sample6_extraAddress').value = extraAddr;
        } else {
          document.getElementById('sample6_extraAddress').value = '';
        }

        // 우편번호와 주소 정보를 해당 필드에 넣는다.
        document.getElementById('sample6_postcode').value = data.zonecode;
        document.getElementById('sample6_address').value = addr;
        // 커서를 상세주소 필드로 이동한다.
        document.getElementById('sample6_detailAddress').focus();
      },
    }).open();
  }
}

/* 이메일 @선택, 띄워쓰기 불가*/
{
  const $email_l = document.getElementById('email-l');
  const $email2 = document.getElementById('email2');
  const $checkS = /[ ]/;
  $email2.addEventListener('keyup', e => {
    if (e.target.value.trim().length == 0) {
      $email_l.style.display = 'none';
    } else {
      $email_l.style.display = 'block';
    }
    if ($checkS.test(e.target.value)) {
      e.target.value = e.target.value.substring(0, e.target.value.length - 1);
    }
  });
  const $sel1 = document.querySelector('#email-l .sel1');
  const $sel2 = document.querySelector('#email-l .sel2');
  const $sel3 = document.querySelector('#email-l .sel3');
  const $sel4 = document.querySelector('#email-l .sel4');
  const $sel5 = document.querySelector('#email-l .sel5');
  const $sel6 = document.querySelector('#email-l .sel6');
  const $sel7 = document.querySelector('#email-l .sel7');

  $sel1.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
  $sel2.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
  $sel3.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
  $sel4.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
  $sel5.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
  $sel6.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
  $sel7.addEventListener('mousedown', e => {
    const selectedEmail = e.target.textContent;
    const curStr = $email2.value.trim();
    $email2.value = `${curStr}${selectedEmail}`;
  });
}

/* 휴대폰번호 */
{
  const $phone = document.getElementById('phone');
  const $checkS = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/;
  $phone.addEventListener('keyup', e => {
    if ($checkS.test(e.target.value)) {
      e.target.value = e.target.value.substring(0, e.target.value.length - 1);
    }
  });
}

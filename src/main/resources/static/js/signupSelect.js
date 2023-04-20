//이벤트 타겟 설정
const $title1 = document.querySelector('.main__title a:nth-child(1)');
const $title2 = document.querySelector('.main__title a:nth-child(2)');
const $personMember = document.querySelector('.main__personMember');
const $companyMember = document.querySelector('.main__companyMember');

//토글 기능 -> 개인회원으로 전환하는 로직
const changPersonMember = () => {
  if($personMember.classList.contains('active')) {
    $personMember.classList.toggle('active');
    $companyMember.classList.toggle('active');
  }
  if ($title1.classList.contains('active')) {
    $title2.classList.toggle('active');
    $title1.classList.toggle('active');
  }
};
//이벤트 리스너 설정
$title1.addEventListener('click', changPersonMember);

//토글 기능 -> 기업회원으로 전환하는 로직
const changCompanyMember = () => {
  if(!$companyMember.classList.contains('active')) {
    $companyMember.classList.toggle('active');
    $personMember.classList.toggle('active');
    $title2.classList.toggle('active');
    $title1.classList.toggle('active');
  }
  if (!$title2.classList.contains('active')) {
    $title2.classList.toggle('active');
    $title1.classList.toggle('active');
  }
};
//이벤트 리스너 설정
$title2.addEventListener('click', changCompanyMember);
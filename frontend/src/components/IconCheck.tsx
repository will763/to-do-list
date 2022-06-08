interface Props {
    completed: boolean;
    update: () => void;
}

function IconCheck({completed, update}: Props ) {
    return(
        <div 
          className="w-5 h-5 dark:bg-[#4d5066] bg-[#e4e5f1] rounded-full
           cursor-pointer grid place-items-center hover:icon-check"
           onClick={update}
           >
            <div className={completed ? "container-check container-check-active"
             : 'container-check dark:bg-[#25273c] bg-[#fafafa] '}
            >
              { completed
                ? <svg xmlns="http://www.w3.org/2000/svg" width="11" height="9"><path fill="none" stroke="#FFF" strokeWidth="2" d="M1 4.304L3.696 7l6-6"/></svg>
                : null
              }
            </div>
        </div>
    )
}

export default IconCheck;
function func() {
    const file = document.getElementById('file').files[0];


    const sampleObject = { //JSONにするオブジェクト
        "isUseDefaultWordList": true,
    }

    const formData = new FormData()
    formData.append('file', file)
    formData.append('jsonValue', new Blob([JSON.stringify(sampleObject)], {type: 'application/json'}))

    axios.post('http://localhost:8080/word-list', formData)

}
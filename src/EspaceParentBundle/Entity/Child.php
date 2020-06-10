<?php

namespace EspaceParentBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\File;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Child
 *
 * @ORM\Table(name="child")
 * @ORM\Entity(repositoryClass="EspaceParentBundle\Repository\childRepository")
 */
class Child
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="lastname", type="string", length=255)
     */
    private $lastname;

    /**
     * @var string
     *
     * @ORM\Column(name="firstname", type="string", length=255)
     */
    private $firstname;

    /**
     * @var int
     *
     * @ORM\Column(name="level", type="integer")
     */
    private $level;

    /**
     * @var int
     *
     * @ORM\Column(name="parent_id", type="integer")
     */
    private $parentId;


    /**
     * @ORM\ManyToOne(targetEntity="\ClassBundle\Entity\Classe")
     * @ORM\JoinColumn(name="class_id" ,referencedColumnName="id")
     */
    private $classId;
    /**
     * @ORM\ManyToOne(targetEntity="\AppBundle\Entity\User")
     * @ORM\JoinColumn(name="teacher_id" ,referencedColumnName="id")
     */
    private $teacher_id;

    /**
     * @var int
     *
     * @ORM\Column(name="age", type="integer")
     */
    private $age;

    /**
     * @var string
     *
     * @ORM\Column(name="photo", type="string", length=255)
     */
    private $photo;
    /**
     * @Assert\File(maxSize="500k")
     */
    public $file;

    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set lastname
     *
     * @param string $lastname
     *
     * @return child
     */
    public function setLastname($lastname)
    {
        $this->lastname = $lastname;

        return $this;
    }

    /**
     * Get lastname
     *
     * @return string
     */
    public function getLastname()
    {
        return $this->lastname;
    }

    /**
     * Set firstname
     *
     * @param string $firstname
     *
     * @return child
     */
    public function setFirstname($firstname)
    {
        $this->firstname = $firstname;

        return $this;
    }

    /**
     * Get firstname
     *
     * @return string
     */
    public function getFirstname()
    {
        return $this->firstname;
    }

    /**
     * Set level
     *
     * @param integer $level
     *
     * @return child
     */
    public function setLevel($level)
    {
        $this->level = $level;

        return $this;
    }

    /**
     * Get level
     *
     * @return int
     */
    public function getLevel()
    {
        return $this->level;
    }

    /**
     * Set parentId
     *
     * @param integer $parentId
     *
     * @return child
     */
    public function setParentId($parentId)
    {
        $this->parentId = $parentId;

        return $this;
    }

    /**
     * Get parentId
     *
     * @return int
     */
    public function getParentId()
    {
        return $this->parentId;
    }

    /**
     * Set classId
     *
     * @param \ClassBundle\Entity\Classe $classId
     *
     * @return child
     */
    public function setClassId(\ClassBundle\Entity\Classe $classId)
    {
        $this->classId = $classId;

        return $this;
    }

    /**
     * Get classId
     *
     * @return \ClassBundle\Entity\Classe
     */
    public function getClassId()
    {
        return $this->classId;
    }

    /**
     * Set age
     *
     * @param integer $age
     *
     * @return child
     */
    public function setAge($age)
    {
        $this->age = $age;

        return $this;
    }

    /**
     * Get age
     *
     * @return int
     */
    public function getAge()
    {
        return $this->age;
    }

    /**
     * Set photo
     *
     * @param string $photo
     *
     * @return child
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;

        return $this;
    }

    /**
     * Get photo
     *
     * @return string
     */
    public function getPhoto()
    {
        return $this->photo;
    }
    /**
     * Set teacher_id
     *
     * @param \EspaceParentBundle\Entity\teacher $teacher_id
     *
     * @return child
     */
    public function setTeacher_id(\EspaceParentBundle\Entity\teacher $teacher_id = null)
    {
        $this->classId = $teacher_id;

        return $this;
    }
    public function getWebPath()
    {
        return null===$this->photo ? null :$this->getUploadDir().''.$this->photo();
    }
    protected function getUploadRootDir()
    {
        return _DIR_.'/../../../../web'.$this->getUploadDir();
    }
    protected function getUploadDir()
    {
        return 'images';
    }
    public function uploadProfilePicture()
    {
        if( $this->file==null) {
            $this->photo= $this->photo;

        }
        else {

            $this->file->move($this->getUploadDir(), $this->file->getClientOriginalName());
            $this->photo = $this->file->getClientOriginalName();
            $this->file = null;
        }
    }
    /**
     * Get teacherId
     *
     * @return \EspaceParentBundle\Entity\teacher
     */
    public function teacherId()
    {
        return $this->teacher_id;
    }
}

